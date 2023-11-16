package com.example.demo.controller;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.helper.ResponseMessageBuilder;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> add(@RequestBody UserRegisterDto user) throws Exception{
        try{
            userService.insertUser(user);
            return ResponseEntity.ok(ResponseMessageBuilder.build("Success create user!"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }
}
