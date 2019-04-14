package ru.kogut.enterprise.ws.users;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author S.Kogut on 2019-04-14
 */

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "username",
        "login",
        "password",
        "email"
})
@XmlRootElement(name = "userResponse")
public class UserResponse extends ResultResponse{

    private String username;

    private String login;

    private String password;

    private String email;

}
