package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final LoginDto user) {
        final Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        authenticationManager.authenticate(auth);
        final User loggedInUser = userService.getByEmail(user);

        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);

        final Map<String, Object> claims = new HashMap<>();
        claims.put("exp", cal.getTime());
        claims.put("id", loggedInUser.getId());

        final Map<String, Object> response = new HashMap<>();
        response.put("Token",jwtService.generateJwt(claims));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
