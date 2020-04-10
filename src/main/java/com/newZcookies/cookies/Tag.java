package com.newZcookies.cookies;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String Name;

    public Tag(String name){
        Name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<Recipe> Recipes;

    public Long getId() {
        return Id;
    }


    public String getName() {
        return Name;
    }
}
