package com.newZcookies.cookies.repository;

import com.newZcookies.cookies.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
