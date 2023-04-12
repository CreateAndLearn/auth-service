package com.work.mapper;

import com.work.dto.UserDto;
import com.work.entity.User;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User userDtoToUser(UserDto userDto) {

    return User.builder()
        .id(UUID.randomUUID())
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .email(userDto.getEmail())
        .phoneNo(userDto.getPhoneNo())
        .password(userDto.getPassword())
        .build();
  }

  public UserDto userToUserDto(User savedUserObject) {

    return UserDto.builder()
        .id(savedUserObject.getId())
        .firstName(savedUserObject.getFirstName())
        .lastName(savedUserObject.getLastName())
        .email(savedUserObject.getEmail())
        .phoneNo(savedUserObject.getPhoneNo())
        .build();
  }
}
