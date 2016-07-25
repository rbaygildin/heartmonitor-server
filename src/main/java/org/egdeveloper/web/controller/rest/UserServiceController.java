package org.egdeveloper.web.controller.rest;

import org.egdeveloper.data.model.User;
import org.egdeveloper.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
public class UserServiceController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUserAccount(@RequestBody User user){
        return userService.createUserAccount(user);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public void deleteUserAccount(@PathVariable Long userId){
        userService.deleteUserAccount(userId);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public void updateUserAccount(@RequestBody User user){
        userService.updateUser(user);
    }

    @RequestMapping(value = "/user")
    public User authUser(@RequestParam String login, @RequestParam String password){
        return userService.authUser(login, password);
    }
}
