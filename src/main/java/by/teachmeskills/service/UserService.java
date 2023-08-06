package by.teachmeskills.service;

import by.teachmeskills.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    void createNewUser(User user);

}
