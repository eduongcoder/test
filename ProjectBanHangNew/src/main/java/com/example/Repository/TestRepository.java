package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Test;

public interface TestRepository extends JpaRepository<Test, Integer>{

}
