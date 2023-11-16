package com.example.demo.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_attendances",schema = "hris")
public class Attendance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @JoinColumn(name = "action_id",referencedColumnName = "id")
    @OneToOne
    private Constants action;

    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    @OneToOne
    private Employee employee;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Constants getAction() {
        return action;
    }

    public void setAction(Constants action) {
        this.action = action;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }
}
