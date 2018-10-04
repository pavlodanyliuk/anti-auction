package com.kamai.antiauction.service;

import com.kamai.antiauction.model.User;
import com.kamai.antiauction.repository.interfaces.UserRepository;
import com.kamai.antiauction.repository.jdbc.JdbcUserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long userId ) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public void deleteUser(Long userId) {

    }

    public void changeUserPass(User user) {

    }

}
