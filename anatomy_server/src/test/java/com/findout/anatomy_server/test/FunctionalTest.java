package com.findout.anatomy_server.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.findout.anatomy_server.models.Anatom;
import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.models.Relation;
import com.findout.anatomy_server.services.memory.InMemoryService;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
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
		int modelId = 0;
		Relation relation = service.getRelationWithId(0);
		int fromAnatomId = relation.getFrom();
		int toAnatomId = relation.getTo();
		
		given().contentType("application/json").when().get("/models/" + modelId + "/anatoms/" + fromAnatomId + "/relations/" + relation.getId()).then().
		body("id", equalTo(relation.getId())).body("from", equalTo(fromAnatomId)).body("to", equalTo(toAnatomId));
	}
}
