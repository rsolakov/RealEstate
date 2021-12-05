package com.example.realestate.repository;

import com.example.realestate.model.entity.UserRole;
import com.example.realestate.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(UserRoleEnum user);
}
