package ru.kogut.enterprise.model.dictionary;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kogut.enterprise.model.common.CommonDictionary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author S.Kogut on 2019-04-11
 */

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User extends CommonDictionary {

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "eMail", nullable = true)
    private String eMail;

    @Column(name = "roles")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Role> roles = new ArrayList<>();

}
