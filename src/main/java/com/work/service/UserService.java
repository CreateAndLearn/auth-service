package com.work.service;

import com.work.dto.UserDto;
import java.util.UUID;


public interface UserService {

    UserDto findUserById(UUID userId);
}
