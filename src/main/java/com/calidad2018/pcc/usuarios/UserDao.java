package com.calidad2018.pcc.usuarios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User,Long> {

    User findByUsername(String username);
}
