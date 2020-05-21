package com.newZcookies.cookies.servises;

import com.newZcookies.cookies.Role;
import com.newZcookies.cookies.User;
import com.newZcookies.cookies.repository.RoleRepository;
import com.newZcookies.cookies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new UsernameNotFoundException("Пользователь не найден!");
        return user.get();
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUserName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void deleteUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            userRepository.deleteById(user.getId());
        }
        throw new UsernameNotFoundException("Пользователь не найден!");
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User findUserByUserName(String userName){
        User user = userRepository.findByUserName(userName);
        if(user == null)
            throw new UsernameNotFoundException("Пользователь не найден!");
        return user;
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

}
