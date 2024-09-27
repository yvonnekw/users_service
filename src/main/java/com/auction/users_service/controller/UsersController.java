package com.auction.users_service.controller;

import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/get-all-users")
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> getAllUser() {
        return usersService.getAllUsers();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getUsers() {

        return  "users api ";
    }


}
