package ru.kogut.enterprise.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kogut.enterprise.exception.DataErrorException;
import ru.kogut.enterprise.model.dictionary.User;
import ru.kogut.enterprise.model.dto.UserDTO;
import ru.kogut.enterprise.model.dto.UserDTOResponse;
import ru.kogut.enterprise.model.dto.service.HealthCheck;
import ru.kogut.enterprise.services.implementations.UserService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author S.Kogut on 2019-04-12
 */

@RestController
@RequestMapping(value = "/api/users",produces = APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public Health health(){
        return new HealthCheck().health();
    }

    @GetMapping("/allUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(){
        List<User> userList = userService.findAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user:userList) {
            UserDTO userDTO = new UserDTO();
            mapper.map(user,userDTO);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDTOResponse login(@RequestParam(name = "login") final String login,
                                 @RequestParam(name = "password") final String password){
        User user = userService.findByLoginAndPassword(login, password);
        if (user == null){
            return new UserDTOResponse("User not found");
        }
        return new UserDTOResponse(user.getId());
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTOResponse createUser(@RequestParam(name = "username") final String username,
                                      @RequestParam(name = "login") final String login,
                                      @RequestParam(name = "password") final String password,
                                      @RequestParam(name = "email", required = false) final String email){
        if (username.isEmpty()) return new UserDTOResponse("Username cannot be empty");
        if (login.isEmpty()) return new UserDTOResponse("Login cannot be empty");
        if (password.isEmpty()) return new UserDTOResponse("Password cannot be empty");

        try {
            User user = new User();
            user.setUserName(username);
            user.setLogin(login);
            user.setPassword(password);
            user.setEMail(email);
            user = userService.createUser(user);
            if (user != null & !user.getId().isEmpty()) return new UserDTOResponse(user.getId());
        }catch (DataErrorException e){
            return new UserDTOResponse(e.getMessage());
        }

        return new UserDTOResponse("Error create user");
    }

    @GetMapping("/getUserById")
    @ResponseStatus(HttpStatus.OK)
    public UserDTOResponse getUserById(@RequestParam("id") final String id){
        if (id.isEmpty()) return new UserDTOResponse("Id cannot be empty");

        User user = userService.findUserById(id);

        if (user == null) return new UserDTOResponse("User not found");

        UserDTO userDTO = new UserDTO();
        mapper.map(user, userDTO);
        userDTO.setResult(user.getId());

        return userDTO;
    }

}
