package ru.kogut.enterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kogut.enterprise.model.dictionary.Role;

import java.util.Optional;

/**
 * @author S.Kogut on 2019-04-14
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findById(String id);

    void deleteById(String id);

}
