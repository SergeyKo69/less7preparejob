package ru.kogut.enterprise.bootstrap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kogut.enterprise.enums.Roles;
import ru.kogut.enterprise.model.dictionary.Role;
import ru.kogut.enterprise.model.dictionary.User;
import ru.kogut.enterprise.services.implementations.RoleService;
import ru.kogut.enterprise.services.implementations.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author S.Kogut on 2019-04-11
 */

@Component
public class bootstrap implements InitializingBean {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public void afterPropertiesSet() throws Exception {
        initUser("Administrator","Admin", "123", Roles.ADMINISTRATOR);
        initUser("User","User", "123", Roles.USER);
    }

    public void initUser(String userName, String login, String password, Roles role){
        User user = new User();
        user.setIsDelete(false);
        user.setUserName(userName);
        user.setLogin(login);
        user.setPassword(password);
        List<Role> roles = new ArrayList<>();
        Role roleObj = new Role();
        roleObj.setIsDelete(false);
        roleObj.setUser(user);
        roleObj.setRoles(role);
        roles.add(roleObj);
        userService.createUser(user);
        roleService.createRole(roleObj);
        user.setRoles(roles);
        userService.updateUser(user);
    }
}
