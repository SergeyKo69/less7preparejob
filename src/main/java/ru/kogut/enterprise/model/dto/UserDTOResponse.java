package ru.kogut.enterprise.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author S.Kogut on 2019-04-14
 */

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
public class UserDTOResponse {

    private String result;

    public UserDTOResponse(String result) {
        this.result = result;
    }

}
