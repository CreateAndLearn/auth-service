package com.work.service.impl;

import com.work.dto.UserDto;
import com.work.entity.User;
import com.work.mapper.UserMapper;
import com.work.repository.UserRegistrationRepository;
import com.work.service.UserRegistrationService;
import com.work.util.PasswordStorageUtil;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRegistrationImpl implements UserRegistrationService {

  private UserRegistrationRepository userRegistrationRepository;
  private UserMapper userMapper;
  private PasswordStorageUtil passwordStorageUtil;

  public UserRegistrationImpl(UserRegistrationRepository userRegistrationRepository,
      UserMapper userMapper, PasswordStorageUtil passwordStorageUtil) {
    this.userRegistrationRepository = userRegistrationRepository;
    this.userMapper = userMapper;
    this.passwordStorageUtil = passwordStorageUtil;
  }

  public UserDto registerUser(UserDto userDto) {
    User requestUserEntity = userMapper.userDtoToUser(userDto);
    requestUserEntity.setSalt(passwordStorageUtil.generateSalt());
    try {
      requestUserEntity.setPassword(passwordStorageUtil
          .hashPassword(requestUserEntity.getPassword(), requestUserEntity.getSalt()));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      log.error("Error occurred with details : {}", e.getMessage());

    }
    User savedUserObject = userRegistrationRepository.save(requestUserEntity);
    if (savedUserObject != null) {
      log.info("The user is successfully registered {}", savedUserObject);
    }
    return userMapper.userToUserDto(savedUserObject);
  }


}
