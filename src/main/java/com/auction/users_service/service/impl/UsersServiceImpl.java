package com.auction.users_service.service.impl;

import com.auction.users_service.config.KeycloakConfig;
import com.auction.users_service.dto.UsersRequest;
import com.auction.users_service.dto.UsersResponse;
import com.auction.users_service.model.Users;
import com.auction.users_service.repostory.UsersRepository;
//import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
//import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
*/
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
//import org.keycloak.admin.client.Keycloak;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImpl {

    private final UsersRepository usersRepository;
    //@Autowired
    //private final Keycloak keycloak;
    //private final KeycloakConfig keycloakConfig;

    //@Value("${keycloak.realm}")
    //private String realm;

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
        /*
        createUserInKeycloak(usersRequestBody);

        createUserInKeycloak(usersRequestBody);*/
        return new UsersResponse(users.getUserId(), users.getUsername(), users.getPassword(), users.getTelephone(), users.getFirstName(), users.getLastName(), users.getEmailAddress());
    }
    /*
    private void createUserInKeycloak(UsersRequest userRequest) {
        //Keycloak keycloak = KeycloakProvider();

        UserRepresentation keycloakUser = new UserRepresentation();
        keycloakUser.setUsername(userRequest.username());
        keycloakUser.setEmail(userRequest.emailAddress());
        keycloakUser.setFirstName(userRequest.firstName());
        keycloakUser.setLastName(userRequest.lastName());
        keycloakUser.setEnabled(true);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setValue(userRequest.password());
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setTemporary(false);

        keycloakUser.setCredentials(List.of(credentials));
        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(keycloakUser);

        log.info("Status Code " + response.getStatus());

        if(!Objects.equals(201,response.getStatus())){

            throw new RuntimeException("Status code "+response.getStatus());
        }

        log.info("User is created");

        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(userRequest.username(), true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        sendVerificationEmail(userRepresentation1.getId());

    }*/
    /*
   // @Override
    public void sendVerificationEmail(String userId) {

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();

    }*/
/*
   // @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(userId);
    }*/
/*
   // @Override
    public void forgotPassword(String username) {

        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        UserResource userResource = usersResource.get(userRepresentation1.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));


    }*/
/*
   // @Override
    public UserResource getUser(String userId) {
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }*/
/*
    //@Override
    public List<RoleRepresentation> getUserRoles(String userId) {


        return getUser(userId).roles().realmLevel().listAll();
    }
*/
/*
    //@Override
    public List<GroupRepresentation> getUserGroups(String userId) {


        return getUser(userId).groups();
    }*/

/*
    private UsersResource getUsersResource() {
      return   keycloak.realm(realm)
                .users();
    }*/

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
