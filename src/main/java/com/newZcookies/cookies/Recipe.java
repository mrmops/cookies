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
    private List<Long> Comments;

    public Recipe(Long id, String name, List<Long> groups, String description, Double rating, User authorId, List<Long> comments){
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

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public List<Long> getComments() {
        return Comments;
    }

    public void setComments(List<Long> comments) {
        Comments = comments;
    }

    public User getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(User authorId) {
        AuthorId = authorId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Long> getGroups() {
        return Groups;
    }

    public void setGroups(List<Long> groups) {
        Groups = groups;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
