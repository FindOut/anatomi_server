package com.findout.anatomy_server.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Attribute;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.memory.InMemoryService;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

/**
 * Integration tests
 * @author Maria Färdig
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class FunctionalTest {

	private InMemoryService service;
	private TestHelper helper;

	@BeforeClass
	public static void setup() {
		String port = System.getProperty("server.port");
		if (port == null) {
			RestAssured.port = Integer.valueOf(8080);
		} else {
			RestAssured.port = Integer.valueOf(port);
		}

		String basePath = System.getProperty("server.base");
		if (basePath == null) {
			basePath = "/anatomy/api";
		}
		RestAssured.basePath = basePath;

		String baseHost = System.getProperty("server.host");
		if (baseHost == null) {
			baseHost = "http://localhost";
		}
		RestAssured.baseURI = baseHost;

	}

	public FunctionalTest() {
		this.service = InMemoryService.getInstance();
		this.helper = new TestHelper();
	}

	@Test
	public void testSite() {
		get("/").then().assertThat().statusCode(200);
	}

	@Test
	public void testGetModels() {
		helper.deleteAllData();
		helper.addModels();

		given().contentType("application/json").when().get("/models").then().body(containsString("id"));
	}

	@Test
	public void testGetModelWithId() {
		helper.deleteAllData();
		helper.addModels();

		given().contentType("application/json").when().get("/models/" + 0).then().body("id", equalTo(0))
				.body(containsString("anatoms"));
	}

	@Test
	public void testInvalidModel() {
		helper.deleteAllData();
		helper.addModels();

		given().when().get("/models/" + 16).body().equals(null);
	}

	@Test
	public void testDeleteModelWithId() {
		helper.deleteAllData();
		helper.addModels();
		int sizeBefore = service.getModels().size();
		int id = 0;

		given().when().delete("/models/" + id).then().statusCode(200);

		given().when().get("/models/" + id).body().equals(null);

		given().when().get("/models").then().assertThat().body("size()", is(sizeBefore - 1));
	}

	@Test
	public void testAddEmptyModel() {
		helper.deleteAllData();
		helper.addModels();
		int sizeBefore = service.getModels().size();

		given().contentType("application/json").when().post("/models").then().body(containsString("id"))
				.body(containsString("anatoms"));

		given().when().get("/models").then().assertThat().body("size()", is(sizeBefore + 1));
	}
	
	@Test
	public void testGetAnatomsForModel() {
		helper.deleteAllData();
		helper.fillData();
		int id = 0;
		
		given().contentType("application/json").when().get("/models/" + id + "/anatoms").then().
		body(containsString("id")).body(containsString("attributes")).body(containsString("relations"));
	}
	
	@Test
	public void testGetAnatomWithId() {
		helper.deleteAllData();
		helper.fillData();
		int id = 0;
		
		given().contentType("application/json").when().get("/models/" + id + "/anatoms/" + id).then().
		body("id",equalTo(0));
	}
	
	@Test
	public void testAddAnatomToModel() {
		helper.deleteAllData();
		helper.fillData();
		int id = 0;
		int sizeBefore = service.getAnatomsForModel(id).size();
		
		given().contentType("application/json").when().post("/models/" + id + "/anatoms").then().
		body(containsString("id")).body(containsString("attributes")).body(containsString("relations"));
		
		given().when().get("/models/" + id + "/anatoms").then().assertThat().body("size()", is(sizeBefore + 1));
	}
	
	@Test
	public void testDeleteAnatomFromModel() {
		helper.deleteAllData();
		helper.fillData();
		int id = 0;
		int sizeBefore = service.getAnatomsForModel(id).size();
		
		given().when().delete("/models/" + id + "/anatoms/" + id).then().statusCode(200);

		given().when().get("/models/" + id + "/anatoms/" + id).body().equals(null);
		
		given().when().get("/models/" + id + "/anatoms").then().assertThat().body("size()", is(sizeBefore - 1));
	}
	
	@Test
	public void testGetRelationsForAnatom() {
		helper.deleteAllData();
		helper.fillData();
		int id = 0;
		
		given().contentType("application/json").when().get("/models/" + id + "/anatoms/" + id + "/relations").then().
		body(containsString("id")).body(containsString("from")).body(containsString("to"));
	}
	
	@Test
	public void testGetRelationWithId() {
		helper.deleteAllData();
		helper.fillData();
		Relation relation = service.getRelationWithId(0);
		int relationId = relation.getId();
		int fromAnatomId = relation.getFrom();
		int toAnatomId = relation.getTo();
		
		given().contentType("application/json").when().get("/relations/" + relationId).then().
		body("id", equalTo(relationId)).body("from", equalTo(fromAnatomId)).body("to", equalTo(toAnatomId));
	}
	
	@Test
	public void testAddRelation() {
		helper.deleteAllData();
		helper.addModels();
		helper.addAnatoms();
		int modelId = 0;
		int fromAnatomId = 0;
		int toAnatomId = 1;
		int beforeFrom = service.getRelationsForAnatom(fromAnatomId).size();
		int beforeTo = service.getRelationsForAnatom(toAnatomId).size();
		Map<String,String> relation = new HashMap<String, String>();
		relation.put("from", String.valueOf(fromAnatomId));
		relation.put("to", String.valueOf(toAnatomId));
		
		given().contentType("application/json").body(relation).when().post("/relations").then().
		body("id", equalTo(service.getRelationsForAnatom(fromAnatomId).get(0).getId())).body("from", equalTo(fromAnatomId)).body("to", equalTo(toAnatomId));

		given().when().get("/models/" + modelId + "/anatoms/" + fromAnatomId + "/relations").then().assertThat().body("size()", is(beforeFrom + 1));
		
		given().when().get("/models/" + modelId + "/anatoms/" + toAnatomId + "/relations").then().assertThat().body("size()", is(beforeTo + 1));
	}
	
	@Test
	public void testDeleteRelationWithId() {
		helper.deleteAllData();
		helper.fillData();
		Relation relation = service.getRelationWithId(0);
		int relationId = relation.getId();
		int fromAnatomId = relation.getFrom();
		int toAnatomId = relation.getTo();
		int beforeFrom = service.getRelationsForAnatom(fromAnatomId).size();
		int beforeTo = service.getRelationsForAnatom(toAnatomId).size();
		
		given().when().delete("/relations/" + relationId).then().statusCode(200);

		given().when().get("/relations/" + relationId).body().equals(null);
		
		given().when().get("/models/" + 0 + "/anatoms/" + fromAnatomId + "/relations").then().assertThat().body("size()", is(beforeFrom - 1));
		
		given().when().get("/models/" + 0 + "/anatoms/" + toAnatomId + "/relations").then().assertThat().body("size()", is(beforeTo - 1));
	}
	
	@Test
	public void testGetAttributesForAnatom() {
		helper.deleteAllData();
		helper.fillData();
		int id = 0;
		
		given().contentType("application/json").when().get("/models/" + id + "/anatoms/" + id + "/attributes").then().
		body(containsString("id")).body(containsString("value"));
	}
	
	@Test
	public void testGetAttributesForRelation() {
		helper.deleteAllData();
		helper.fillData();
		int id = 0;
		
		given().contentType("application/json").when().get("/relations/" + id + "/attributes").then().
		body(containsString("id")).body(containsString("value"));
	}
	
	@Test
	public void testGetAttributeWithId() {
		helper.deleteAllData();
		helper.fillData();
		Attribute attribute = service.getAttributeWithId(0);
		int attributeId = attribute.getId();
		
		given().contentType("application/json").when().get("/attributes/" + attributeId).then().
		body("id", equalTo(attributeId)).body("value", equalTo(attribute.getValue()));
	}
	
	@Test
	public void testAddAttributeToAnatom() {
		helper.deleteAllData();
		helper.addModels();
		helper.addAnatoms();
		int modelId = 0;
		int anatomId = 0;
		int sizeBefore = service.getAttributesForAnatom(anatomId).size();
		Map<String,String> attribute = new HashMap<String, String>();
		attribute.put("value", "1");
		
		given().contentType("application/json").body(attribute).when().post("/models/" + modelId + "/anatoms/" + anatomId + "/attributes").then().
		body("id", equalTo(service.getAttributesForAnatom(anatomId).get(0).getId())).body("value", equalTo(1));

		given().when().get("/models/" + modelId + "/anatoms/" + anatomId + "/attributes").then().assertThat().body("size()", is(sizeBefore + 1));
	}
	
	@Test
	public void testAddAttributeToRelation() {
		helper.deleteAllData();
		helper.addModels();
		helper.addAnatoms();
		helper.addRelations();
		int relationId = 0;
		int sizeBefore = service.getAttributesForRelation(relationId).size();
		Map<String,String> attribute = new HashMap<String, String>();
		attribute.put("value", "1");
		
		given().contentType("application/json").body(attribute).when().post("/relations/" + relationId + "/attributes").then().
		body("id", equalTo(service.getAttributesForRelation(relationId).get(0).getId())).body("value", equalTo(1));

		given().when().get("/relations/" + relationId + "/attributes").then().assertThat().body("size()", is(sizeBefore + 1));
	}
	
	@Test
	public void testChangeValueInAttribute() {
		helper.deleteAllData();
		helper.fillData();
		int modelId = 0;
		int anatomId = 0;
		int attributeId = service.getAnatomsForModel(modelId).get(anatomId).getAttributes().get(0).getId();
		Map<String,String> attribute = new HashMap<String, String>();
		attribute.put("value", "6");
		
		given().contentType("application/json").body(attribute).when().put("/attributes/" + attributeId).then().
		body("id", equalTo(attributeId)).body("value", equalTo(6));
	}
	
	@Test
	public void testDeleteAttributeFromRelation() {
		helper.deleteAllData();
		helper.fillData();
		Relation relation = service.getRelationWithId(0);
		int relationId = relation.getId();
		int attributeId = relation.getAttributes().get(0).getId();
		int sizeBefore = service.getAttributesForRelation(relationId).size();
		
		given().when().delete("/relations/" + relationId + "/attributes/" + attributeId).then().statusCode(200);

		given().when().get("/attributes/" + attributeId).body().equals(null);
		
		given().when().get("/relations/" + relationId + "/attributes").then().assertThat().body("size()", is(sizeBefore - 1));
	}
	
	@Test
	public void testDeleteAttributeFromAnatom() {
		helper.deleteAllData();
		helper.fillData();
		int modelId = 0;
		int anatomId = 0;
		Anatom anatom = service.getAnatomWithId(anatomId);
		int attributeId = anatom.getAttributes().get(0).getId();
		int sizeBefore = service.getAttributesForAnatom(anatomId).size();
		
		given().when().delete("/models/" + modelId + "/anatoms/" + anatomId + "/attributes/" + attributeId).then().statusCode(200);

		given().when().get("/attributes/" + attributeId).body().equals(null);
		
		given().when().get("/models/" + modelId + "/anatoms/" + anatomId + "/attributes").then().assertThat().body("size()", is(sizeBefore - 1));
	}
	
	@Test
	public void testGetOutboundRelationsForAnatom() {
		helper.deleteAllData();
		helper.fillData();
		int modelId = 0;
		int fromAnatomId = service.getAnatoms().get(0).getId();
		int toAnatomId = service.getAnatoms().get(2).getId();
		service.addRelation(fromAnatomId, toAnatomId);
		
		JsonPath jsonPath = when().get("/models/" + modelId + "/anatoms/" + fromAnatomId + "/relations/outbound").then().
		extract().body().jsonPath();
		List<Relation> relations = jsonPath.getList("", Relation.class);
		for (Relation r : relations) {
			Assert.assertThat(r.getFrom(), is(fromAnatomId));
		}
	}
	
	@Test
	public void testGetInboundRelationsForAnatom() {
		helper.deleteAllData();
		helper.fillData();
		int modelId = 0;
		int fromAnatomId = service.getAnatoms().get(2).getId();
		int toAnatomId = service.getAnatoms().get(1).getId();
		service.addRelation(fromAnatomId, toAnatomId);
		
		JsonPath jsonPath = when().get("/models/" + modelId + "/anatoms/" + toAnatomId + "/relations/inbound").then().
		extract().body().jsonPath();
		List<Relation> relations = jsonPath.getList("", Relation.class);
		for (Relation r : relations) {
			Assert.assertThat(r.getTo(), is(toAnatomId));
		}
	}
}
