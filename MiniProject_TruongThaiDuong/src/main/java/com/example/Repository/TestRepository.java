package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.TestEntity;

public interface  TestRepository extends JpaRepository<TestEntity, Integer>{

}
