package com.example.demo.controller;

import com.example.demo.helper.ResponseMessageBuilder;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getDetail(){
        try{
            return new ResponseEntity<>(employeeService.getDetail(), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    @GetMapping("/subordinates")
    public ResponseEntity<?> getDetailSubordinates(){
        try{
            return new ResponseEntity<>(employeeService.getSubordinateDetails(), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> add(Employee employee){
        try{
            employeeService.add(employee);
            return ResponseEntity.ok(ResponseMessageBuilder.build("Success create attendance check in!"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }
}
