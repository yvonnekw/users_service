package com.auction.users_service.mapper;

import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UsersResponse fromUser(Users user) {
        return new UsersResponse(
                user.getUserId(),
                user.getUsername(),
                user.getTelephone(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress()
                );
    }
}

