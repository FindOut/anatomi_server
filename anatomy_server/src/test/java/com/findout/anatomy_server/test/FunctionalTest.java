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

import com.findout.anatomy_server.models.Model;
import com.findout.anatomy_server.services.AnatomyService;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class FunctionalTest {

	private final String apiBase = "http://localhost:8080/anatomy/api";
	private TestService service;

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
		this.service = new TestService();
	}

	@Test
	public void testSite() {
		get(apiBase).then().assertThat().statusCode(200);
	}

	@Test
	public void testGetModels() {
		service.deleteAllModels();
		service.addModels();

		given().contentType("application/json").when().get(apiBase + "/models").then().body(containsString("id"));
	}

	@Test
	public void testGetModelWithId() {
		service.deleteAllModels();
		service.addModels();

		given().contentType("application/json").when().get(apiBase + "/models/" + 0).then().body(containsString("id"))
				.body(containsString("anatoms"));
	}

	@Test
	public void testInvalidModel() {
		service.deleteAllModels();
		service.addModels();

		given().when().get(apiBase + "/models/" + 16).body().equals(null);
	}

	@Test
	public void deleteModelWithId() {
		service.deleteAllModels();
		service.addModels();
		int sizeBefore = service.getModels().size();
		int id = 0;

		given().when().delete(apiBase + "/models/" + id).then().statusCode(200);

		given().when().get(apiBase + "/models/" + id).body().equals(null);

		given().when().get(apiBase + "/models").then().assertThat().body("size()", is(sizeBefore - 1));
	}

	@Test
	public void addEmptyModel() {
		service.deleteAllModels();
		service.addModels();
		int sizeBefore = service.getModels().size();

		given().contentType("application/json").when().post(apiBase + "/models").then().body(containsString("id"))
				.body(containsString("anatoms"));

		given().when().get(apiBase + "/models").then().assertThat().body("size()", is(sizeBefore + 1));
	}
	
	@Test
	public void getAnatomsForModel() {
		service.deleteAllModels();
		Model model = service.addModel();
		int id = model.getId();
		service.addAnatomsToModel(id);
		
		given().contentType("application/json").when().get(apiBase + "/models/" + id + "/anatoms").then().
		body(containsString("id")).body(containsString("attributes")).body(containsString("relations"));
	}
	
	@Test
	public void addAnatomToModel() {
		service.deleteAllModels();
		Model model = service.addModel();
		int id = model.getId();
		int sizeBefore = service.getAnatomsForModel(id).size();
		
		given().contentType("application/json").when().post(apiBase + "/models/" + id + "/anatoms").then().
		body(containsString("id")).body(containsString("attributes")).body(containsString("relations"));
		
		given().when().get(apiBase + "/models/" + id + "/anatoms").then().assertThat().body("size()", is(sizeBefore + 1));
	}
	
	@Test
	public void deleteAnatomFromModel() {
		service.deleteAllModels();
		Model model = service.addModel();
		int modelId = model.getId();
		service.addAnatomsToModel(modelId);
		int anatomId = 0;
		int sizeBefore = service.getAnatomsForModel(modelId).size();
		
		given().when().delete(apiBase + "/models/" + modelId + "/anatoms/" + anatomId).then().statusCode(200);

		given().when().get(apiBase + "/models/" + modelId + "/anatoms/" + anatomId).body().equals(null);
		
		given().when().get(apiBase + "/models/" + modelId + "/anatoms").then().assertThat().body("size()", is(sizeBefore - 1));
		
		
	}
}
