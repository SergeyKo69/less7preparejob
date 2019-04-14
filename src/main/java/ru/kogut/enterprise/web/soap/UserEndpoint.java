package ru.kogut.enterprise.web.soap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.kogut.enterprise.exception.DataErrorException;
import ru.kogut.enterprise.model.dictionary.User;
import ru.kogut.enterprise.model.dto.UserDTO;
import ru.kogut.enterprise.model.dto.UserDTOResponse;
import ru.kogut.enterprise.model.dto.service.HealthCheck;
import ru.kogut.enterprise.services.implementations.UserService;
import ru.kogut.enterprise.ws.users.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author S.Kogut on 2019-04-14
 */

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://kogut.ru/ws/users";

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserEndpoint(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "healthRequest")
    @ResponsePayload
    public HealthRequest health(){
        HealthRequest healthRequest = new HealthRequest();
        healthRequest.setResult(new HealthCheck().health().toString());
        return healthRequest;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "allUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(){
        List<User> userList = userService.findAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user:userList) {
            UserDTO userDTO = new UserDTO();
            mapper.map(user,userDTO);
            userDTOList.add(userDTO);
        }
        GetAllUsersResponse getAllUsersResponse = new GetAllUsersResponse();
        getAllUsersResponse.setUserList(userDTOList);
        return getAllUsersResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public ResultResponse login(@RequestPayload final LoginRequest request){
        ResultResponse resultResponse = new ResultResponse();
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user == null){
            resultResponse.setResult("User not found");
        }else{
            resultResponse.setResult(user.getId());
        }
        return resultResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public ResultResponse login(@RequestPayload final CreateUserRequest request){
        ResultResponse resultResponse = new ResultResponse();
        try {
            User user = new User();
            user.setUserName(request.getUsername());
            user.setLogin(request.getLogin());
            user.setPassword(request.getPassword());
            user.setEMail(request.getEmail());
            user = userService.createUser(user);
            resultResponse.setResult(user.getId());
            if (user != null & !user.getId().isEmpty()) return resultResponse;
        }catch (DataErrorException e){
            resultResponse.setResult(e.getMessage());
            return resultResponse;
        }
        resultResponse.setResult("Error create user");
        return resultResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public UserResponse getUserById(@RequestPayload final GetUserByIdRequest request){
        User user = userService.findUserById(request.getId());

        UserResponse userResponse = new UserResponse();

        if (user == null){
           userResponse.setResult("User not found");
           return userResponse;
        }

        mapper.map(user, userResponse);
        userResponse.setResult(user.getId());

        return userResponse;
    }
}
