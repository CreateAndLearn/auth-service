package com.work.controller;

import com.work.dto.UserDto;
import com.work.service.UserService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping(value = "v1/user/{userId}")
  public ResponseEntity<UserDto> findUserById(@PathVariable UUID userId){
    UserDto user = userService.findUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping(value = "v1/register")
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    UserDto user = userService.registerUser(userDto);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

}
