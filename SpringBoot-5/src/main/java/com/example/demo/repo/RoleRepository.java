package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
