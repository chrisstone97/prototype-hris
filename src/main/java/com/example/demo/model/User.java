package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "t_users",schema = "hris")
public class User {
    public static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    @OneToOne
    private Employee employee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
