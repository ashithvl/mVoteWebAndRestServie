package com.mvote.controller;

import com.mvote.models.Users;
import com.mvote.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebAppLoginController {

    @Autowired
    IUserService iUserService;


    @RequestMapping("/")
    public String loginHome(Model model) {
        Users users = new Users();
        model.addAttribute("username", users.getUsername());
        model.addAttribute("password", users.getPassword());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);

        Users users = iUserService.getUserByUserNameAndPassword(username, password);
        if (username.isEmpty()) {
            model.addAttribute("errorUsername", "The User Name must not be Empty.");
            return "login";
        } else if (password.isEmpty()) {
            model.addAttribute("errorPassword", "The Password must not be Empty.");
            return "login";
        }else if (users != null && users.getIsAdmin() == 1) {
            return "redirect:/election";
        }else {
            model.addAttribute("invalidLogin", "Invalid Username and Password for Admin");
        }

        return "login";
    }
}
