package ru.kogut.enterprise.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author S.Kogut on 2019-04-11
 */

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
public class UserDTO extends UserDTOResponse{

    @NotNull
    private String userName;

    @NotNull
    private String login;

    @NotNull
    private String eMail;

}
