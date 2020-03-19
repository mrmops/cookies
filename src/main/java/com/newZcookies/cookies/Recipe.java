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
    private List<Long> Groups;

    @Column
    private String Description;

    @Column
    private Double Rating;

    @OneToMany
    private User AuthorId;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "commentId")
    private List<Comment> Comments;

    public Recipe(Long id, String name, List<Long> groups, String description, Double rating, User authorId, List<Comment> comments){
        Id = id;
        Name = name;
        Groups = groups;
        Description = description;
        Rating = rating;
        AuthorId = authorId;
        Comments = comments;
    }

    public String GetName(){
        return Name;
    }
}
