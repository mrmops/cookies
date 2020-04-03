package com.newZcookies.cookies;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "name")
    private String Name;

    @Column
    private String Description;

    @Column
    private Double Rating;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User Author;

    @ManyToMany
    @JoinTable(name = "recipe_tags",
            joinColumns = { @JoinColumn(name = "recipe_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> Tags;

    public Recipe(String name, String description, Double rating, User author, Set<Tag> tags){
        Name = name;
        Description = description;
        Rating = rating;
        Author = author;
        Tags = tags;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public User getAuthor() {
        return Author;
    }

    public void setAuthor(User author) {
        Author = author;
    }

    public Set<Tag> getTags() {
        return Tags;
    }

    public void setTags(Set<Tag> tags) {
        Tags = tags;
    }

    public Long getId() {
        return Id;
    }
}
