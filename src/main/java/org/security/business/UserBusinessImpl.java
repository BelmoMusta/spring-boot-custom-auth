package org.security.business;


import org.security.dao.RoleRepository;
import org.security.dao.UserRepository;
import org.security.entities.Role;
import org.security.entities.Token;
import org.security.entities.User;
import org.security.service.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class UserBusinessImpl implements UserBusiness {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenBusinessImpl tokenBusiness;


    @Override
    public User addUser(User u) {
        //u.setPassword(u.getPassword());
        return userRepository.save(u);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User u, Long id) {
        u.setId(id);
        //  u.setPassword((u.getPassword()));
        return userRepository.saveAndFlush(u);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User setRoleToUser(Long iduser, Long idrole) {
        User u = userRepository.findOne(iduser);
        Role r = roleRepository.findOne(idrole);
        //u.getRoles().add(r);
        u.setRole(r);
        userRepository.save(u);
        return u;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public boolean checkPassword(Long id, String password) {
        User u = userRepository.findOne(id);
        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (Objects.equals(password, u.getPassword()))
            return true;
        else
            return false;
    }

    @Override
    public Token getToken(UserCredentials credentails) {
        String password = credentails.getPassword();
        String username = credentails.getUser();

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                Predicate passwordPredicate = cb.equal(root.get("password"), password);
                Predicate userPredicate = cb.equal(root.get("username"), username);

                return cb.and(passwordPredicate, userPredicate);


            }
        };

     User user =  userRepository.findOne(specification);

        Token token = new Token();

        user.setTokens(new ArrayList<>());

        token.setTokenString(UUID.randomUUID().toString());

        return tokenBusiness.setTokenForUser(user,token);

    }

    @Override
    public User getUser(String token) {

        Specification<Token> specification = new Specification<Token>() {
            @Override
            public Predicate toPredicate(Root<Token> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                Predicate tokenPredicate = cb.equal(root.get("tokenString"), token);
                return cb.and(tokenPredicate);
            }
        };

        Token t = tokenBusiness.findOne(specification);
        if (t != null) {

            return t.getUser();
        }

        return null;


    }

    @Override
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }



    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       User user= userRepository.getUserByUsername(s);
        if (user == null) {
            throw new UnauthorizedUserException("User doesn't exist!");
        }
        List<Role>roles= (List<Role>) user.getRoles();
        Role role=roles.get(0);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(role.getRole())));
    }*/
}

