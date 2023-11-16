package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.helper.ResponseMessageBuilder;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void insertUser(UserRegisterDto userReg){
        try{
            User user = new User();
            user.setEmail(userReg.getEmail());
            String encodedPassword = passwordEncoder.encode(userReg.getPassword());
            user.setPassword(encodedPassword);
            Employee employee = employeeDao.getById(userReg.getEmployeeId());
            user.setEmployee(employee);
            userDao.add(user);
        }catch (Exception e){
            throw new ValidationException(
                    ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.getByEmail(username);
        if(user != null) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("Email not found!");
    }

    public User getByEmail(LoginDto data) {

        final User user = userDao.getByEmail(data.getEmail());

        return user;
    }
}
