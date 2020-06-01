package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.Recipe;
import com.newZcookies.cookies.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
