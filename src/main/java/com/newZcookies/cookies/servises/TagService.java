package com.newZcookies.cookies.servises;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Tag;
import com.newZcookies.cookies.repository.RecipeRepository;
import com.newZcookies.cookies.repository.TagRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    TagRepository tagRepository;


    public List<Tag> findAllTags(){
        return tagRepository.findAll();
    }

    public Tag findTagById(Long id) throws NotFoundException {
        Optional<Tag> DB_tag = tagRepository.findById(id);
        if(DB_tag.isPresent())
            return  DB_tag.get();
        else
            throw new NotFoundException("Tag not found");
    }

    public void saveTag(Tag tag){
        tagRepository.save(tag);
    }

    public List<Tag> findByNameContains(String name){
        return tagRepository.findByNameContainsIgnoreCase(name);
    }

}
