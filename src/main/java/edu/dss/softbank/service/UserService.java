package edu.dss.softbank.service;

import java.util.List;

import edu.dss.softbank.domain.User;

public interface UserService {
    void saveUser(User user);
    void updateUser(User user);

    User findUserByEmail(String email);
    User findUserById(long id);
    void deleteById(long id);
    List<User> findAllUsers();
}
