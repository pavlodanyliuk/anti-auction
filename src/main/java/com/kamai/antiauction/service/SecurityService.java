package com.kamai.antiauction.service;

import com.kamai.antiauction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User encodePass(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

//    public User verificationToken(User user) {
//        user.setToken(UUID.randomUUID().toString());
//        return user;
//    }

    public boolean comparePass(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

}
