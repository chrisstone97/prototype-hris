package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "t_positions",schema = "hris")
public class Position {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @JoinColumn(name = "parent_position_id",referencedColumnName = "id")
    @OneToOne
    private Position parentPosition;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Position getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(Position parentPosition) {
        this.parentPosition = parentPosition;
    }
}
