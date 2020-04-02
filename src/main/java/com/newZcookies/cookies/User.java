package com.newZcookies.cookies;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "login")
    private String Login;

    @Column(name = "name")
    private String Name;

    @Column(name = "second_name")
    private String SecondName;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "recipes")
    private List<Recipe> Recipes;

    public User(){

    }

    public User(String login, String name, String secondName, List<Recipe> recipes) {
        Login = login;
        Name = name;
        SecondName = secondName;
        Recipes = recipes;
    }
}
