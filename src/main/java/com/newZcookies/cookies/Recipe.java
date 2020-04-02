package com.newZcookies.cookies;

import javax.persistence.*;
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
    private User Author;

    public Recipe(Long id, String name, String description, Double rating, User author){
        Id = id;
        Name = name;
        Description = description;
        Rating = rating;
        Author = author;
    }

    public String GetName(){
        return Name;
    }
}
