package ru.kogut.enterprise.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kogut.enterprise.exception.DataErrorException;
import ru.kogut.enterprise.model.dictionary.User;
import ru.kogut.enterprise.repository.UserRepository;
import ru.kogut.enterprise.services.interfaces.UserInt;

import java.util.List;
import java.util.Optional;

/**
 * @author S.Kogut on 2019-04-12
 */

@Service
public class UserService implements UserInt {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(User user) throws DataErrorException {
        validateUser(user);
//        encodePassword(user);
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) throws DataErrorException{
        if (user.getId() == null || user.getId().equals("") ) throw new DataErrorException("Id cannot be empty");
//        encodePassword(user);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DataErrorException {
        if (login.isEmpty()) throw new DataErrorException("Login cannot be empty");
        if (password.isEmpty()) throw new DataErrorException("Password cannot be empty");
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public User findUserById(String id) throws DataErrorException{
        if (id == null || id.equals("")) throw new DataErrorException("User id cannot be empty");
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.orElseThrow(() -> new DataErrorException("Error get user by Id. User not found"));
        return userOptional.get();
    }

    @Override
    public User findByUserName(String userName) throws DataErrorException {
        if (userName == null || userName.equals("")) throw new DataErrorException("UserName cannot be empty");
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public void deleteUser(User user) throws DataErrorException{
        validateUser(user);
        if (user.getId() == null || user.getId().equals("")) throw new DataErrorException("User id cannot empty");
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) throws DataErrorException {
        if (id == null || id.equals("")) throw new DataErrorException("Id cannot be empty");
        userRepository.findById(id);
    }

    public void encodePassword(User user){
        final String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
   }

    public void validateUser(User user) throws DataErrorException {
        if (user == null) throw new DataErrorException("User cannot be null");
        if (user.getLogin() == null || user.getLogin().equals("")) throw new DataErrorException("User login cannot be empty");
        if (user.getUserName() == null || user.getUserName().equals("")) throw new DataErrorException("UserName cannot be empty");
        if (user.getPassword() == null || user.getPassword().equals("")) throw new DataErrorException("Password cannot be empty");
    }
}
