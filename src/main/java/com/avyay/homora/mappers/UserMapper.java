package com.avyay.homora.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.avyay.homora.dtos.UserDTO;
import com.avyay.homora.entities.UserEntity;
import com.avyay.homora.requests.UserRequest;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toUserEntity(UserDTO userDTO);
    UserDTO toUserDTO(UserEntity userEntity);

    UserDTO toUserDTO(UserRequest userRequest);
}
