package com.newZcookies.cookies;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String Name;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "groupId")
    private List<Tag> Group;

    @Column
    private String Description;

    @Column
    private Double Rating;

    @OneToMany
    private User AuthorId;
    private List<Comment> Comments;

    public Recipe(Long id, String name, List<Tag> group, String description, Double rating, User authorId, List<Comment> comments){
        Id = id;
        Name = name;
        Group = group;
        Description = description;
        Rating = rating;
        AuthorId = authorId;
        Comments = comments;
    }

    public String GetName(){
        return Name;
    }
}
