package com.auction.users_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest {
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String telephone;
}
