package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Permissions;

public interface IPermissionsRepository extends JpaRepository<Permissions, Integer> {

}
