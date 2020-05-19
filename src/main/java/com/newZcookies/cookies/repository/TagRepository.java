package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
}