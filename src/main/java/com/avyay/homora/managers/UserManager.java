package com.avyay.homora.managers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avyay.homora.dtos.UserDTO;
import com.avyay.homora.entities.UserEntity;
import com.avyay.homora.mappers.UserMapper;
import com.avyay.homora.repos.UserRepo;

@Component
public class UserManager {
    @Autowired
    private UserRepo userRepo;

    public void save(UserDTO userDTO) {
        userRepo.save(UserMapper.INSTANCE.toUserEntity(userDTO));
    }

    public UserDTO getByEmail(String email) {
        Optional<UserEntity> optionalUserEntity = userRepo.findByEmail(email);

        if (optionalUserEntity.isPresent()) {
            return UserMapper.INSTANCE.toUserDTO(optionalUserEntity.get());
        } else {
            return null;
        }
    }
}
