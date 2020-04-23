package com.newZcookies.cookies.servises;

import com.newZcookies.cookies.Comment;
import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.repository.CommentRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }

    public Comment findCommentById(Long id) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent())
            throw new NotFoundException("Комментарий не найден!");
        return comment.get();
    }

    public void deleteCommentById(Long id) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if(!comment.isPresent())
            throw new NotFoundException("Комментарий не найден!");
        commentRepository.deleteById(id);
    }

    public List<Comment> findAllByRecipeId(Long recipe_id){
        return commentRepository.findAllByRecipeIdOrderByTimeOfCreateAsc(recipe_id);
    }
}
