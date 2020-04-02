package com.newZcookies.cookies;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="name", unique = true)
    private String Name;

    public Tag(String name){
        Name = name;
    }

    public Long getId() {
        return Id;
    }


    public String getName() {
        return Name;
    }
}
