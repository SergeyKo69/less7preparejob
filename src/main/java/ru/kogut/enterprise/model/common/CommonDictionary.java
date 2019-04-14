package ru.kogut.enterprise.model.common;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.intellij.lang.annotations.Identifier;
import ru.kogut.enterprise.enums.Roles;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author S.Kogut on 2019-04-11
 */
@Data
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class CommonDictionary {

    @Id
    @Identifier
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "isDelete")
    private Boolean isDelete;


}
