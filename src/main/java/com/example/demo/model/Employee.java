package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "t_employees",schema = "hris")
public class Employee {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "nik")
    private String nik;

    @JoinColumn(name = "gender_id",referencedColumnName = "id")
    @OneToOne
    private Constants gender;

    @JoinColumn(name = "position_id",referencedColumnName = "id")
    @OneToOne
    private Position position;

    @JoinColumn(name = "employment_type",referencedColumnName = "id")
    @OneToOne
    private Constants employmentType;

    @JoinColumn(name = "supervisor_id",referencedColumnName = "id")
    @OneToOne
    private Employee supervisor;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Constants getGender() {
        return gender;
    }

    public void setGender(Constants gender) {
        this.gender = gender;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Constants getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(Constants employmentType) {
        this.employmentType = employmentType;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
}
