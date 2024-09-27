package com.auction.users_service.service;

import com.auction.users_service.dto.UsersRequest;
import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.model.Users;
import com.auction.users_service.repostory.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersResponse createUser(UsersRequest usersRequestBody) {

        Users users = Users.builder()
                .username(usersRequestBody.username())
                .emailAddress(usersRequestBody.emailAddress())
                .firstName(usersRequestBody.firstName())
                .lastName(usersRequestBody.lastName())
                .telephone(usersRequestBody.telephone())
                .build();
        usersRepository.save(users);
        log.info("User with id {} is saved. ", users.getUserId());
        return new UsersResponse(users.getUserId(), users.getUsername(), users.getPassword(), users.getTelephone(), users.getFirstName(), users.getLastName(), users.getEmailAddress());
    }

    public List<UsersResponse> getAllUsers() {
       return usersRepository.findAll()
               .stream()
               .map(users -> new UsersResponse(users.getUserId(), users.getUsername(), users.getPassword(), users.getTelephone(), users.getFirstName(), users.getLastName(), users.getEmailAddress()))
               .toList();
    }

    public UsersResponse makeUserSeller(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setSeller(true);
        usersRepository.save(user);
        log.info("User with id {} is now marked as a seller.", userId);

        return new UsersResponse(user.getUserId(), user.getUsername(), null, user.getTelephone(), user.getFirstName(), user.getLastName(), user.getEmailAddress());
    }


    public UsersResponse makeUserBuyer(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setBuyer(true);
        usersRepository.save(user);
        log.info("User with id {} is now marked as a buyer.", userId);

        return new UsersResponse(user.getUserId(), user.getUsername(), null, user.getTelephone(), user.getFirstName(), user.getLastName(), user.getEmailAddress());
    }


    public boolean isUserSeller(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return user.isSeller();
    }

    public boolean isUserBuyer(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return user.isBuyer();
    }

}
