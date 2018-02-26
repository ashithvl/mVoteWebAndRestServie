package com.mvote.Controller;

import com.mvote.models.Users;
import com.mvote.service.IUserService;
import com.mvote.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getUserByUserNameAndPassword(@RequestBody Users users) {
        Users user = iUserService.getUserByUserNameAndPassword(users.getUsername(), users.getPassword());
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("User with username " + user.getUsername()
                    + " not found"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody Users users) {
        boolean register = iUserService.registerUser(users.getUsername(), users.getPassword(), users.getUserImage(), users.getAge());
        if (register)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorType("Registration"), HttpStatus.NOT_FOUND);
    }

}
