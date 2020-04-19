package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
