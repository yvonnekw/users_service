package com.auction.users_service.dto;

import lombok.*;

//@Data

public record UsersResponse(Long userId, String username, String password, String firstName, String lastName, String emailAddress, String telephone) {

}
