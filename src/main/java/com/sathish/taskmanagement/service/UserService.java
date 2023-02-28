package com.sathish.taskmanagement.service;

import com.sathish.taskmanagement.payload.UserDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
