package ru.kogut.enterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kogut.enterprise.model.dictionary.User;

import java.util.Optional;

/**
 * @author S.Kogut on 2019-04-11
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    User findByUserName(String userName);

    Optional<User> findById(String id);

    void deleteById(String id);

}
