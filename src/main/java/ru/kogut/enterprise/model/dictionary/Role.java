package ru.kogut.enterprise.model.dictionary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kogut.enterprise.enums.Roles;
import ru.kogut.enterprise.model.common.CommonDictionary;

import javax.persistence.*;

/**
 * @author S.Kogut on 2019-04-14
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "userRole")
public class Role extends CommonDictionary {

    @ManyToOne
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Roles roles = Roles.USER;


}
