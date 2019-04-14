package ru.kogut.enterprise.services.interfaces;

import ru.kogut.enterprise.model.dictionary.Role;

import java.util.List;

/**
 * @author S.Kogut on 2019-04-14
 */
public interface RoleInt {

    Role createRole(Role role);

    Role updateRole(Role  role);

    List<Role> findAllRoles();

    Role findRoleById(String id);

    void deleteRole(Role role);

    void deleteRoleById(String id);

}
