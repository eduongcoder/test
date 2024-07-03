package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer>{

}
