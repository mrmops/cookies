package com.newZcookies.cookies;

import javax.persistence.*;

@Entity
public class Appriasal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "recipe")
    private Recipe recipe;

    @Column(name = "value")
    private Integer value;


    public Appriasal(){}

    public Appriasal(User author, Recipe recipe, Integer value){
        this.author = author;
        this.recipe = recipe;
        this.value = value;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
