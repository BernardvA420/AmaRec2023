package com.impact.amarec.repository;

import com.impact.amarec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String email);

        User findUserByName(String name);
}