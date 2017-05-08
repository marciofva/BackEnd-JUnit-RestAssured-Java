package com.correiosRestAssuredCall;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class RestAssuredCalls extends RestAssuredConfig {

	/**
	 * This TEST CASE is aimed at entering a valid CEP and validating the body format
	 */
	@Test
	public void EnterValidCEP() {
		
		when()
			.get("/13040089") // input a valid CEP
		.then()
			.log()
			.all()
			.statusCode(200) // check that the status Code is (200 OK) - Request was successful
			.contentType(ContentType.JSON) // check that the content type return from the API is JSON
			.assertThat().body(matchesJsonSchemaInClasspath("schema-json.json")); // check that the body matches the schema (JSON) 
	}
	
	
	/**
	 * This TEST CASE is aimed at entering a valid CEP and validating all body content
	 */
	@Test
	public void ValidateBodyWithValidCEP(){
		
		when()
		.get("/13040089") // input a valid CEP
	.then()
		.log()
		.all()
		.statusCode(200) // check that the status Code is (200 OK) - Request was successful
		.contentType(ContentType.JSON) // check that the content type return from the API is JSON
		.body(matchesJsonSchemaInClasspath("schema-json.json")) // check that the body matches the schema (JSON) 
		.body("cep", equalTo("13040089")) // Check that the "CEP" value is correct
		.body("tipoDeLogradouro", equalTo("Rua")) // Check that the "TIPO DE LOGRADOURO" value is correct
		.body("logradouro", equalTo("Manoel Sylvestre de Freitas Filho")) // Check that the "LOGRADOURO" value is correct
		.body("bairro", equalTo("Jardim Nova Europa")) // Check that the "BAIRRO" value is correct
		.body("cidade", equalTo("Campinas")) // Check that the "CIDADE" value is correct
		.body("estado", equalTo("SP")); // Check that the "ESTADO" value is correct
	}
	
	
	/**
	 * This TEST CASE is aimed at entering an invalid CEP and validating the response message
	 */
	@Test
	public void EnterInvalidCEP() {

		when()
			.get("/57883") // input an invalid CEP
		.then()
			.log()
			.all()
			.statusCode(404) // check that the status Code is (404 Not Found ) - Required resource could not be found
			.contentType(ContentType.JSON) // check that the content type return from the API is JSON
			.body("message", equalTo("Endereço não encontrado!"));
	}
}
