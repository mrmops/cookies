package com.newZcookies.cookies;

import org.springframework.data.repository.CrudRepository;

public interface UserDataBase extends CrudRepository<User, Long> {
}
