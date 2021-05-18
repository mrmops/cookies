package com.newZcookies.cookies;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User author;

    @ManyToOne
    @JoinColumn(name="recipe_id", nullable=false)
    private Recipe recipe;

    @Column
    private Date timeOfCreate;

    @Column
    @Size(max = 300)
    private String text;

    public Comment(){

    }

    public Comment(User author, Recipe recipe, @Size(max = 300) String text) {
        this.author = author;
        this.recipe = recipe;
        this.text = text;
        LocalDateTime localDateTime = LocalDateTime.now();
        this.timeOfCreate = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getTimeOfCreate() {
        return timeOfCreate;
    }

    public void setTimeOfCreate(Date timeOfCreate) {
        this.timeOfCreate = timeOfCreate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
