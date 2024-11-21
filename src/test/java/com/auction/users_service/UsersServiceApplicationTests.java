package com.auction.users_service;

import io.restassured.RestAssured;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersServiceApplicationTests {

	//@LocalServerPort
	//private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		//RestAssured.port = port;
		RestAssured.port = 9090;
	}

	@Test
	void shouldCreateUser() {
		String requestBody =
				"""
				{
				   "username" : "Honda",
				    "password" : "pass",
				    "firstName" : "Kim",
				    "lastName" : "Kwebena",
				    "emailAddress" : "goeg@gmail.com",
				    "telephone" : "09988765543"
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/auth/create-user")
				.then()
				.statusCode(201)
				.body("userId", Matchers.notNullValue());
				//.body("firstName", Matchers.equalTo("Kim"))
				//.body("lastName", Matchers.equalTo("Kwebena"))
				//.body("telephone", Matchers.equalTo("09988765543"));
	}

	@Test
	void shouldCreateUserThroughGateway() {
		String requestBody =
				"""
				{
				   "username" : "Honda",
				    "password" : "pass",
				    "firstName" : "Kim",
				    "lastName" : "Kwebena",
				    "emailAddress" : "goeg@gmail.com",
				    "telephone" : "09988765543"
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/auth/create-user")
				.then()
				.statusCode(201)
				.body("userId", Matchers.notNullValue());
		//.body("firstName", Matchers.equalTo("Kim"))
		//.body("lastName", Matchers.equalTo("Kwebena"))
		//.body("telephone", Matchers.equalTo("09988765543"));
	}


}
