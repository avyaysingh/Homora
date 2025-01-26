package com.avyay.homora.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avyay.homora.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
