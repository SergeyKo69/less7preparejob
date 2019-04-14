package ru.kogut.enterprise.ws.users;

import lombok.Getter;
import lombok.Setter;
import ru.kogut.enterprise.model.dto.UserDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "userList",
})
@XmlRootElement(name = "allUsersResponse")
public class GetAllUsersResponse {

    private List<UserDTO> userList;

}
