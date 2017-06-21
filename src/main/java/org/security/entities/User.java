package org.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by admin on 09/06/2017.
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String nom;

    private String prenom;

    private String password;

    private boolean activated;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Collection<Token> tokens ;

    /*@ManyToMany
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Collection<Role> roles;*/
    @ManyToOne
    @JoinColumn(name="idRole")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

   /* public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }*/

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String username, String nom, String prenom, String password, boolean activated) {
        super();
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.activated = activated;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Collection<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<Token> tokens) {
        this.tokens = tokens;
    }
}
