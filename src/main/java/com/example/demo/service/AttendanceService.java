package com.example.demo.service;

import com.example.demo.dao.AttendanceDao;
import com.example.demo.dao.ConstantDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.attendance.AttendanceDto;
import com.example.demo.dto.attendance.ListAttendanceDto;
import com.example.demo.helper.ResponseMessageBuilder;
import com.example.demo.model.Attendance;
import com.example.demo.model.Constants;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private ConstantDao constantDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PrincipalService principalService;

    @Transactional
    public void add(Constants constants){
        try{
            Attendance attendances = new Attendance();
            attendances.setEmployee(principalService.getEmployee());
            attendances.setActionDate(LocalDateTime.now());
            attendances.setAction(constants);
            attendanceDao.insert(attendances);
        }catch (Exception e){
            throw new ValidationException(ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    @Transactional
    public void attendanceCheckIn(){
        Constants constants = constantDao.getByKey("CIN");
        if(constants.getId() != null){
            add(constants);
        }
    }

    @Transactional
    public void attendanceCheckOut(){
        Constants constants = constantDao.getByKey("COU");
        if(constants.getId() != null){
            add(constants);
        }
    }

    public List<AttendanceDto> getAttendances(){
        Employee employee = principalService.getEmployee();
        List<Attendance> attendances = attendanceDao.getAttendances(employee.getId());
        List<AttendanceDto> response = new ArrayList<>();
        for(Attendance data : attendances){
            AttendanceDto dto = new AttendanceDto();
            dto.setId(data.getId());
            dto.setAction(data.getAction().getDescription());
            dto.setActionDate(data.getActionDate());
            response.add(dto);
        }
        return response;
    }

    public List<ListAttendanceDto> getSubordinatesAttendance(){
        Employee employee = principalService.getEmployee();
        List<ListAttendanceDto> response = new ArrayList<>();
        List<Employee> subordinates = employeeDao.getSubordinates(employee.getId());
        if(subordinates.size() > 0){
            for(Employee dataEmployee : subordinates){
                ListAttendanceDto dto = new ListAttendanceDto();
                dto.setEmployeeName(dataEmployee.getName());
                List<Attendance> attendances = attendanceDao.getAttendances(dataEmployee.getId());
                List<AttendanceDto> employeeAttendances = new ArrayList<>();
                for(Attendance dataAttendance : attendances){
                    AttendanceDto employeeAttendance = new AttendanceDto();
                    employeeAttendance.setId(dataAttendance.getId());
                    employeeAttendance.setAction(dataAttendance.getAction().getDescription());
                    employeeAttendance.setActionDate(dataAttendance.getActionDate());
                    employeeAttendances.add(employeeAttendance);
                }
                dto.setListAttendance(employeeAttendances);
                response.add(dto);
            }
            return response;
        }else{
            throw new ValidationException(ResponseMessageBuilder.build("You don't have any subordinates!"));
        }
    }
}
