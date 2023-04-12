package com.work.service;

import com.work.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserRegistrationService {

  UserDto registerUser(UserDto userDto);

}
