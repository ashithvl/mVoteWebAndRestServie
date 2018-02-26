package com.mvote.Controller;

import com.mvote.models.Users;
import com.mvote.service.IUserService;
import com.mvote.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping("/hi")
    public String hi(){
        return "hi";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity getUserByUserNameAndPassword(@PathVariable String username, @PathVariable String password) {
        Users users = iUserService.getUserByUserNameAndPassword(username, password);
        if (users != null)
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("User with username " + username
                    + " not found"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@PathVariable String username, @PathVariable String password,
                                       @PathVariable String userImage, @PathVariable int age) {
        boolean register = iUserService.registerUser(username, password, userImage, age);
        if (register)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("Registration"), HttpStatus.NOT_FOUND);
    }

}
