package com.correiosRestAssuredCall;

import org.junit.Before;
import io.restassured.RestAssured;

public class RestAssuredConfig {

	@Before
	public void setUp() {
		RestAssured.baseURI = "http://correiosapi.apphb.com";
		RestAssured.basePath = "/cep";
	}
}
