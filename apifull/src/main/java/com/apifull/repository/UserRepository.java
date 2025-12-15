package com.apifull.repository;

import com.apifull.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByRut(String rut);
    boolean existsByPhone(String phone);
    Optional<User> findByEmail(String email);
}