package com.auction.users_service.dto;


public record UsersRequest(Long userId, String username, String password, String firstName, String lastName, String emailAddress, String telephone) {

}
