package ru.kogut.enterprise.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kogut.enterprise.exception.DataErrorException;
import ru.kogut.enterprise.model.dictionary.Role;
import ru.kogut.enterprise.repository.RoleRepository;
import ru.kogut.enterprise.services.interfaces.RoleInt;

import java.util.List;
import java.util.Optional;

/**
 * @author S.Kogut on 2019-04-14
 */

@Service
public class RoleService implements RoleInt {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Role createRole(Role role) throws DataErrorException {
        validateRole(role);
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Role role) throws DataErrorException{
        validateRole(role);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(String id) throws DataErrorException {
        if (id.isEmpty()) throw new DataErrorException("Id cannot be empty");
        Optional<Role> roleOptional = roleRepository.findById(id);
        roleOptional.orElseThrow(() -> new DataErrorException("Role not found"));
        return roleOptional.get();
    }

    @Override
    @Transactional
    public void deleteRole(Role role) throws DataErrorException {
        validateRole(role);
        roleRepository.delete(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(String id) throws DataErrorException {
        if (id.isEmpty()) throw new DataErrorException("Id cannot be empty");
        roleRepository.deleteById(id);
    }

    public void validateRole(Role role) throws DataErrorException {
        if (role == null || role.getUser() == null || role.getRoles() == null || role.getRoles().equals("")){
            throw new DataErrorException("Role is not correct");
        }
    }
}
