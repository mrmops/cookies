package com.newZcookies.cookies;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NaturalId
    private String Login;

    @Column(name = "name")
    private String Name;

    @Column(name = "second_name")
    private String SecondName;

    @OneToMany(mappedBy="Author")
    private Set<Recipe> Recipes;

    public User(String login, String name, String secondName) {
        Login = login;
        Name = name;
        SecondName = secondName;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public Set<Recipe> getRecipes() {
        return Recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        Recipes = recipes;
    }

    public void addRecipe(Recipe recipe){
        Recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe){
        Recipes.remove(recipe);
    }

    public void removeRecipeById(Long id){
        for (Recipe e: Recipes) {
            if(e.getId().equals(id)){
                Recipes.remove(e);
                break;
            }
        }
    }

    public Long getId() { return Id; }

    public User(){}
}
