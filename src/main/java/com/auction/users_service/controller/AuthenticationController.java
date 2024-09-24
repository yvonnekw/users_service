package com.auction.users_service.controller;


import com.auction.users_service.dto.UsersRequest;
import com.auction.users_service.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> registerUser(@RequestBody UsersRequest requestBody) {

        return  null;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getUsers() {

        return  "users auth api ";
    }
}
