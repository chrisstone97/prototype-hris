package com.example.demo.controller;

import com.example.demo.helper.ResponseMessageBuilder;
import com.example.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/attendances")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(value = "/checkin")
    public ResponseEntity<?> checkIn(){
        try{
            attendanceService.attendanceCheckIn();
            return ResponseEntity.ok(ResponseMessageBuilder.build("Success create attendance check in!"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    @PostMapping(value = "/checkout")
    public ResponseEntity<?> checkOut(){
        try{
            attendanceService.attendanceCheckOut();
            return ResponseEntity.ok(ResponseMessageBuilder.build("Success create attendance check out!"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAttendances(){
        try{
            return new ResponseEntity<>(attendanceService.getAttendances(),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    @GetMapping(value = "/subordinates")
    public ResponseEntity<?> getSubordinateAttendances(){
        try{
            return new ResponseEntity<>(attendanceService.getSubordinatesAttendance(),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessageBuilder.build(e.getMessage()));
        }
    }
}
