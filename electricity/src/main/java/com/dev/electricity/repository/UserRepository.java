package com.dev.electricity.repository;


import com.dev.electricity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsUserByUsername(String username);

    List<User> findByFullName(String username);
}
