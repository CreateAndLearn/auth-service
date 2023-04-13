package com.work.service.impl;

import com.work.dto.UserDto;
import com.work.entity.User;
import com.work.mapper.UserMapper;
import com.work.repository.UserRepository;
import com.work.service.UserService;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserDto findUserById(UUID userId) {
    log.info("Get User By Id {}", userId);
    Optional<User> userOptional = userRepository.findById(userId);
    User user = userOptional
        .orElseThrow(() -> new NoSuchElementException("No user exists with the id"));
    UserDto userDto = userMapper.userToUserDto(user);
    log.info("Success response for Get User By Id "
        + ": {}", userDto);
    return userDto;
  }

}
