package com.sathish.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotMatch extends RuntimeException{
    private String message;
    public UserNotMatch(String message){
        super(message);
        this.message = message;
    }
}
