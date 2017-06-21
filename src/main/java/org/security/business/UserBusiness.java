package org.security.business;


import org.security.entities.Token;
import org.security.entities.User;
import org.security.service.UserCredentials;

import java.util.List;

public interface UserBusiness {
    public User addUser(User u);

    public List<User> getUsers();

    public User updateUser(User u, Long id);

    public void deleteUser(Long id);
    
    public User setRoleToUser(Long iduser, Long idrole);

    public User getUserByUsername(String username);

    public boolean checkPassword(Long id,String password);

    Token getToken(UserCredentials credentails);

    User getUser(String token);

    User getUser(Long id);
}

