package com.auction.users_service;

import com.auction.users_service.dto.UsersRequest;
import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.repostory.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;

import static java.lang.Long.parseLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersServiceApplicationTests {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
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

}
