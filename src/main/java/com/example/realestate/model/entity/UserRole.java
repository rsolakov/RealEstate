package com.example.realestate.model.entity;

import com.example.realestate.model.entity.enums.UserRoleEnum;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRole() {
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
