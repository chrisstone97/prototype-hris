package com.example.demo.dto.attendance;

import java.util.List;

public class ListAttendanceDto {
    private String employeeName;
    private List<AttendanceDto> listAttendance;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<AttendanceDto> getListAttendance() {
        return listAttendance;
    }

    public void setListAttendance(List<AttendanceDto> listAttendance) {
        this.listAttendance = listAttendance;
    }
}
