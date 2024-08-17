package org.example.aggregator.controller;

import org.example.aggregator.service.UserService;
import org.example.user.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInformation getUserInformation(@RequestParam("userId") Integer userId){
        return this.userService.getUserInformation(userId);
    }

}
