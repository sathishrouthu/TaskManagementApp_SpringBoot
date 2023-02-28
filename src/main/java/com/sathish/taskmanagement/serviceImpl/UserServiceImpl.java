package com.sathish.taskmanagement.serviceImpl;

import com.sathish.taskmanagement.entity.User;
import com.sathish.taskmanagement.payload.UserDto;
import com.sathish.taskmanagement.repository.UserRepository;
import com.sathish.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepo;

    @Override
    public UserDto createUser(@RequestBody UserDto userDto){
        User savedUser = userrepo.save(userDtoToEntity(userDto));
        return entityTouserDto(savedUser);
    }
    private User userDtoToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
    private UserDto entityTouserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getPassword());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
