package com.auction.users_service.controller;

import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.service.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersServiceImpl;

    /*
 The configuration for the users on this level is not working meaning any user can
 get all users whether. This could be due to the fact that the config is created on
 the api gateway level
  */
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/get-all-users")
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> getAllUser() {
        return usersServiceImpl.getAllUsers();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getUsers() {

        return  "users api ";
    }


}
