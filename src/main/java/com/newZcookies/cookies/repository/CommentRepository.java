package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Comment;
import com.newZcookies.cookies.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByRecipeIdOrderByTimeOfCreateAsc(Long recipe_id);
}
