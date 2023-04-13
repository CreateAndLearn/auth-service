package com.work.service.impl;

import com.work.dto.UserDto;
import com.work.entity.User;
import com.work.mapper.UserMapper;
import com.work.repository.UserRepository;
import com.work.service.UserService;
import com.work.util.PasswordStorageUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
  private PasswordStorageUtil passwordStorageUtil;

  public UserServiceImpl(UserRepository userRepository,
      UserMapper userMapper, PasswordStorageUtil passwordStorageUtil) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordStorageUtil = passwordStorageUtil;
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

  @Override
  public UserDto registerUser(UserDto userDto) {
    User requestUserEntity = userMapper.userDtoToUser(userDto);
    requestUserEntity.setSalt(passwordStorageUtil.generateSalt());
    try {
      requestUserEntity.setPassword(passwordStorageUtil
          .hashPassword(requestUserEntity.getPassword(), requestUserEntity.getSalt()));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      log.error("Error occurred with details : {}", e.getMessage());

    }
    User savedUserObject = userRepository.save(requestUserEntity);
    if (savedUserObject != null) {
      log.info("The user is successfully registered {}", savedUserObject);
    }
    return userMapper.userToUserDto(savedUserObject);
  }

}
