package com.newZcookies.cookies;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Set;


@Entity
@Table(name = "Recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @Size(max = 30, message = "Не более 30 символов")
    private String name;

    @Column
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

    @ElementCollection
    @CollectionTable(name = "appraisals",
            joinColumns = @JoinColumn(name = "recipe_id"))
    @MapKeyJoinColumn(name = "user_id")
    @Column(name = "appraisal")
    private Map<User, Integer> appraisals;

    public Recipe(String name, String description, User author){
        this.name = name;
        this.description = description;
        this.author = author;
        this.rating = 0.0;
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
        double sum = 0;
        int count = 0;
        for (int num:appraisals.values()) {
            sum += num;
            count++;
        }
        return count == 0 ? 0 : sum / count ;
    }

    public void setRating(Double rating) {
        rating =  getRating();
    }

    public void updateRating() {
        rating =  getRating();
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

    public Map<User, Integer> getAppraisals() {
        return appraisals;
    }

    public void setAppraisals(Map<User, Integer> appraisals) {
        this.appraisals = appraisals;
    }

    public void addAppraisals(User user, int appraisal){
        appraisals.put(user, appraisal);
        updateRating();
    }
}

