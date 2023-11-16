package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalService {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private UserDao userDao;

    @SuppressWarnings("unchecked")
    public Employee getEmployee(){
        final Map<String, Object> principals = (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = (String) principals.get("id");

        User user = userDao.getById(userId);

        return user.getEmployee();
    }
}
