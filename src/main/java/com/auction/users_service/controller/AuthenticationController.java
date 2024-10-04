package com.auction.users_service.controller;


import com.auction.users_service.config.KeycloakConfig;
import com.auction.users_service.dto.UsersRequest;
import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.service.impl.UsersServiceImpl;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.KeyException;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final UsersServiceImpl usersServiceImpl;


    @PostMapping("/create-user")
    @PreAuthorize("hasRole('client_user') or hasRole('client_admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponse registerUser(@RequestBody UsersRequest requestBody) {
        try {
            return usersServiceImpl.createUser(requestBody);
        } catch (Exception e) {
            log.error("An error occurred while creating the user: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e);
        }
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getUsers() {

        return  "users auth api ";
    }
}
