package com.newZcookies.cookies;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public Tag(String name){
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<Recipe> recipes;

    public Tag(){}

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }
}
