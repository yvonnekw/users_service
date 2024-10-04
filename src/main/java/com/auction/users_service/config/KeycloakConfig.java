package com.auction.users_service.config;

import lombok.*;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

//@Configuration
//@NoArgsConstructor
//@AllArgsConstructor
public class KeycloakConfig {
/*
    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.grant-type}")
    private String grantType;

    @Value("${keycloak.admin-id}")
    private String adminId;

    @Value("${keycloak.admin-secret}")
    private String adminSecret;
/*
    @Value("${keycloak.username}")
    private String username;

    @Value("${keycloak.password}")
    private String password;*/
/*
    @Bean
    public Keycloak keycloak(){

        return KeycloakBuilder.builder()
                .clientSecret(clientSecret)
                .clientId(clientId)
                .grantType("client_credentials")
                .realm(realm)
                .serverUrl(serverUrl)
                .build();
    }
*/
}
