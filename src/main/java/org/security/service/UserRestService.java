package org.security.service;


import org.security.business.TokenBusiness;
import org.security.business.UserBusiness;
import org.security.config.HasRole;
import org.security.entities.Token;
import org.security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserRestService {
    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    TokenBusiness tokenBuisness;


    @CrossOrigin
    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public User addUser(@RequestBody User u) {
        return userBusiness.addUser(u);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userBusiness.getUsers();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User u, @PathVariable Long id) {
        return userBusiness.updateUser(u, id);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userBusiness.deleteUser(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/addRoleToUser/{iduser}/{idrole}", method = RequestMethod.PUT)
    public User setRoleToUser(@PathVariable Long iduser, @PathVariable Long idrole) {
        return userBusiness.setRoleToUser(iduser, idrole);
    }

    @CrossOrigin
    @RequestMapping(value = "/guest/user/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username) {
        return userBusiness.getUserByUsername(username);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/userPassword/{id}", method = RequestMethod.GET)
    public boolean checkPassword(@PathVariable Long id, @RequestParam String password) {
        return userBusiness.checkPassword(id, password);
    }


    @CrossOrigin
    @HasRole("admin")
    @RequestMapping(value = "/api/getUSer/", method = RequestMethod.POST)
    public Token getUser(@RequestBody UserCredentials credentails) {

        return userBusiness.getToken(credentails);
    }


    @CrossOrigin
    @HasRole("admin")
    @RequestMapping(value = "/api/getUSer/{id}", method = RequestMethod.POST)
    public User getUser(@PathVariable Long id) {

        return userBusiness.getUser(id);
    }


    @HasRole("admin")
    @CrossOrigin
    @RequestMapping(value = "/api/token/", method = RequestMethod.POST)
    public User getUserByToken(@RequestParam(name = "token", defaultValue = "") String token) {
        return tokenBuisness.getUser(token);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/not_found/", method = RequestMethod.GET)
    public String notFound() {
        return "not found";
    }


}