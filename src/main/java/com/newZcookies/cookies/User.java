package com.newZcookies.cookies;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @OneToMany(mappedBy="author", cascade = CascadeType.REMOVE)
    private Set<Recipe> recipes;

    public User(String login, String name, String secondName) {
        this.login = login;
        this.name = name;
        this.secondName = secondName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Long getId() { return id; }

    public User(){}
}
