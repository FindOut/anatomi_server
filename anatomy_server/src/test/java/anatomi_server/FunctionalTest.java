package anatomi_server;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class FunctionalTest {

	@Test
    public void testSite() {
		get("http://localhost:8080").then().assertThat().statusCode(200);
    }
}
