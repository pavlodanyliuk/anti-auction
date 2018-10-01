package com.kamai.antiauction.controller;

import com.kamai.antiauction.model.User;
import com.kamai.antiauction.model.transfer.validation.UserValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    public UserController() {
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        User user = new User();
        user.setName("Pahs");
        user.setId(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@Validated(UserValidation.New.class) @RequestBody User user) {

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@Validated @RequestBody User user) {

        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}


