package pt.com.visualnuts.number;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class NumberVerifierControllerIT {
	
	@Test
	public void shouldGetVisual() {
		get("api/numbers-validate/3").then().statusCode(200).assertThat()
		.body(containsString("\"1\",\"2\",\"Visual \""));
	}

	@Test
	public void shouldGetNuts() {
		get("api/numbers-validate/5").then().statusCode(200).assertThat()
		.body(containsString("\"1\",\"2\",\"Visual \",\"4\",\"Nuts\""));
	}

	@Test
	public void shouldGetVisualNuts() {
		get("api/numbers-validate/15").then().statusCode(200).assertThat()
		.body(containsString(",\"Visual Nuts\""));
	}

	@Test
	public void shouldGetExceptionForWrongParam() {
		get("api/numbers-validate/0").then().statusCode(400).assertThat()
		.body(containsString("{\"statusCode\":400,\"message\":\"Parametro incorreto por favor informe um valido\"}"));
	}
}
