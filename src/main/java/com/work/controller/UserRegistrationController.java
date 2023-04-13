package com.work.controller;


import com.work.dto.UserDto;
import com.work.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserRegistrationController {

  private UserRegistrationService userRegistrationService;

  public UserRegistrationController(UserRegistrationService userRegistrationService) {
    this.userRegistrationService = userRegistrationService;
  }

  @PostMapping(value = "v1/register")
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    UserDto user = userRegistrationService.registerUser(userDto);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

}
