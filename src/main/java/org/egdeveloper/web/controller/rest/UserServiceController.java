package org.egdeveloper.web.controller.rest;

import org.egdeveloper.data.model.Confident;
import org.egdeveloper.data.model.SecurityToken;
import org.egdeveloper.data.model.User;
import org.egdeveloper.service.IKeyGenerator;
import org.egdeveloper.service.IUserService;
import org.egdeveloper.utils.exceptions.AuthorizationException;
import org.egdeveloper.utils.exceptions.SecurityTokenIlegalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserServiceController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IKeyGenerator tokenGenerator;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUserAccount(@RequestBody User user){
        return userService.createUserAccount(user);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
//    public void deleteUserAccount(@PathVariable Long userId, @RequestParam String secToken){
    public void deleteUserAccount(@PathVariable Long userId){
        User user = userService.findUserById(userId);
//        if(secToken == null || secToken.isEmpty())
//            throw new AuthorizationException();
//        if(!user.getSecTokens().contains(secToken))
//            throw new SecurityTokenIlegalException();
        userService.deleteUserAccount(userId);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
//    public void updateUserAccount(@RequestBody User user, @RequestParam String secToken){
    public void updateUserAccount(@RequestBody User user){
//        if(secToken == null || secToken.isEmpty())
//            throw new AuthorizationException();
//        if(!prevUser.getSecTokens().contains(secToken))
//            throw new SecurityTokenIlegalException();
        userService.updateUser(user);
    }

    @RequestMapping(value = "/user")
    public Map authUser(@RequestParam String login, @RequestParam String password){
        return userService.authUser(login, password);
    }

    @RequestMapping(value = "/user/{userId}/confident", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public Confident addConfident(@RequestBody Confident confident, @PathVariable Long userId, @RequestParam String secToken){
    public Confident addConfident(@RequestBody Confident confident, @PathVariable Long userId){
        return userService.addConfident(userId, confident);
    }

    @RequestMapping(value = "/user/{userId}/confident", produces = "application/json")
//    public Collection<Confident> findConfident(@PathVariable Long userId, @RequestParam String secToken){
    public Collection<Confident> findConfident(@PathVariable Long userId){
        User user = userService.findUserById(userId);
//        if(secToken == null || secToken.isEmpty())
//            throw new AuthorizationException();
//        if(!user.getSecTokens().contains(secToken))
//            throw new SecurityTokenIlegalException();
        return userService.findConfident(userId);
    }

    @RequestMapping(value = "/user/{userId}/device", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public void registerDevice(@PathVariable Long userId, @RequestBody String securityToken, @RequestParam String defSecToken){
    public void registerDevice(@PathVariable Long userId, @RequestBody String securityToken){
        User user = userService.findUserById(userId);
//        if(defSecToken == null || defSecToken.isEmpty())
//            throw new AuthorizationException();
//        if(!user.getSecTokens().contains(defSecToken))
//            throw new SecurityTokenIlegalException();
        SecurityToken token = new SecurityToken();
        token.setGuid(securityToken.trim());
        userService.registerDevice(user, token);
    }

    @RequestMapping(value = "/user/credentials", produces = "application/json")
    public User findUserByToken(@RequestParam String token){
        User user = userService.findUserByToken(token.trim());
        if(user == null)
            throw new SecurityTokenIlegalException();
        return user;
    }
}
