package org.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by admin on 09/06/2017.
 */
@Entity
public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    //@ManyToMany(mappedBy = "roles")
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Collection<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Role(String role) {
        super();
        this.role = role;
    }

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }

}