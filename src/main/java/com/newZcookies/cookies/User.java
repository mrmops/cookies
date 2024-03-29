package com.newZcookies.cookies;


import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @Size(min = 4, message = "Имя пользователя должно быть не меньше 4 символов")
    private String userName;

    @Column(name = "password")
    @Size(min=6, message = "Пароль должен быть не меньше 6 символов")
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @OneToMany(mappedBy="author", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Appriasal> appriasals;

    public User(String userName, String name, String secondName) {
        this.userName = userName;
        this.name = name;
        this.secondName = secondName;
    }

    public User() {
    }
    
    public boolean isAdmin(){
        List<Role> result = new ArrayList<Role>();
        for (Role role: roles) {
            if(role.getName().equals("ROLE_ADMIN")){
                result.add(role);
            }
        }
        return !result.isEmpty();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUserName(String login) {
        this.userName = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getUserName(){
        return userName;
    }
}
