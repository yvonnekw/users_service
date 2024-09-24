package com.auction.users_service;

import com.auction.users_service.dto.UsersRequest;
import com.auction.users_service.repostory.UsersRepository;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class UsersServiceApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UsersRepository usersRepository;

	@Container
	PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16");
	//TestcontainersConfiguration testcontainersConfiguration = new TestcontainersConfiguration();
	//PostgreSQLContainer<?> post = testcontainersConfiguration.postgresContainer();
			//post.("integration-tests-db")
			//.withUsername("sa")
			//.withPassword("sa");


	@Autowired
	private MockMvc mockMvc;
/*
	@DynamicPropertySource
	void postgresProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", post::getJdbcUrl);
		registry.add("spring.datasource.username", post ::getUsername);
		registry.add("spring.datasource.password", post::getPassword);
	}*/


	@Test
	void shouldCreateUser() throws Exception {
		UsersRequest usersRequest = getUserRequest();
		String usersRequestString = objectMapper.writeValueAsString(usersRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create-user")
		.contentType(MediaType.APPLICATION_JSON)
				.content(usersRequestString))
				.andExpect(status().isCreated());
		Assertions.assertTrue(usersRepository.findAll().size() == 1);
	}

	private UsersRequest getUserRequest() {
		return UsersRequest.builder()
				.username("kofi")
				.password("pass")
				.telephone("43095823045")
				.firstName("Popper")
				.lastName("Webengine")
				.emailAddress("kofi.gmail.com")
				.build();
	}

}
