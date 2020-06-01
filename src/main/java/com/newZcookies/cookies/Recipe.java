package com.newZcookies.cookies;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;


@Entity
@Table(name = "Recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @Size(max = 50, message = "Не более 30 символов")
    private String name;

    @Column(length=5000)
    @Size(max = 5000, message = "Не более 5000 символов")
    private String description;

    @Column
    private Double rating;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User author;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "recipe_tags",
            joinColumns = { @JoinColumn(name = "recipe_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private Set<Appriasal> appraisals;

    @OneToMany(mappedBy="recipe", cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    public Recipe(String name, String description, User author, Set<Tag> tags){
        this.name = name;
        this.description = description;
        this.author = author;
        this.rating = 0.0;
        this.tags = tags;
    }

    public Recipe(){}

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return BigDecimal.valueOf(updateRating()).setScale(1, RoundingMode.DOWN).doubleValue();
    }

    public void setRating() {
        this.rating =  updateRating();
    }

    public Double updateRating() {
        double sum = 0;
        int count = 0;
        for (Appriasal num:appraisals) {
            sum += num.getValue();
            count++;
        }
        return count == 0 ? 0 : sum / count;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {    this.tags = tags;    }

    public Long getId() {
        return id;
    }

    public Set<Appriasal> getAppraisals() {
        return appraisals;
    }

    public void setAppraisals(Set<Appriasal> appraisals) {
        this.appraisals = appraisals;
    }

    public Appriasal getUserFromAppriasals(User user){
        for (Appriasal a: appraisals
             ) {
            if(a.getAuthor().equals(user))
                return a;
        }
        return null;
    }

    public boolean containsAppriasalsByUser(User user){
        return getUserFromAppriasals(user) != null;
    }

    public Integer getAppriasalValueByUser(User user){
        return getUserFromAppriasals(user).getValue();
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}

