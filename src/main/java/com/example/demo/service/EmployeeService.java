package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.employee.EmployeeDto;
import com.example.demo.helper.ResponseMessageBuilder;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PrincipalService principalService;

    public void add(Employee employee){
        try{
            employeeDao.insert(employee);
        }catch (Exception e){
            throw new ValidationException(ResponseMessageBuilder.build(e.getMessage()));
        }
    }

    public EmployeeDto getDetail(){
        Employee employee = principalService.getEmployee();
        EmployeeDto response = new EmployeeDto();

        response.setName(employee.getName());
        response.setNik(employee.getNik());
        response.setGender(employee.getGender().getDescription());
        response.setPosition(employee.getPosition().getName());
        response.setEmploymentType(employee.getEmploymentType().getDescription());
        response.setSupervisorName(employee.getSupervisor().getName());

        return response;
    }

    public List<EmployeeDto> getSubordinateDetails(){
        List<Employee> employees = employeeDao.getSubordinates(principalService.getEmployee().getId());
        List<EmployeeDto> response = new ArrayList<>();
        for(Employee data : employees){
            EmployeeDto dto = new EmployeeDto();
            dto.setName(data.getName());
            dto.setNik(data.getNik());
            dto.setGender(data.getGender().getDescription());
            dto.setPosition(data.getPosition().getName());
            dto.setEmploymentType(data.getEmploymentType().getDescription());
            dto.setSupervisorName(data.getSupervisor().getName());
            response.add(dto);
        }
        return response;
    }
}
