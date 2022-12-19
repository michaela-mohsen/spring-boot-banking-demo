package com.banking.springboot.auth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    public List<UserRole> findByUserId(Integer userId);
}
