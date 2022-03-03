package com.Projects.MedStore.controller;

import javax.servlet.http.HttpServletResponse;

import com.Projects.MedStore.Model.User;
import com.Projects.MedStore.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

@Autowired
UserService userService;


    @PostMapping("/addUser")
    public String saveUser(@RequestParam("firstName") String firstName,
    @RequestParam("lastName") String lastName,
    @RequestParam("userName") String userName,
    @RequestParam("email") String email,
    @RequestParam("password") String password,
    HttpServletResponse httpResponse
    )  throws Exception
    {
        User user =new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        userService.saveUser(user);

        httpResponse.sendRedirect("/registrationSuccess");
        return null;
    }


    
}
