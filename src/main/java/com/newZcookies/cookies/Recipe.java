package com.newZcookies.cookies;

import java.util.List;

public class Recipe {
    private long Id;
    private String Name;
    private int Group;
    private String Description;
    private double Rating;
    private long AuthorId;
    private List<Comment> Comments;

    public Recipe(long id, String name, int group, String description, double rating, long authorId, List<Comment> comments){
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
