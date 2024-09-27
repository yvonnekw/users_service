package com.auction.users_service.controller;


import com.auction.users_service.dto.UsersRequest;
import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.model.Users;
import com.auction.users_service.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UsersService usersService;

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponse registerUser(@RequestBody UsersRequest requestBody) {
        return usersService.createUser(requestBody);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getUsers() {

        return  "users auth api ";
    }
}
