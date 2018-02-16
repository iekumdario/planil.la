package com.calidad2018.pcc.usuarios;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {


    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    void save(User user);

    void delete(User user);
}
