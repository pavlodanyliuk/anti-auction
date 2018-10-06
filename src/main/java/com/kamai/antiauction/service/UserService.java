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
    private final SecurityService securityService;

    @Autowired
    public UserService(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    public User saveUser(User user) {

        user.setNotificationSettIsOn(true);

        return userRepository.save(securityService.encodePass(user));
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
