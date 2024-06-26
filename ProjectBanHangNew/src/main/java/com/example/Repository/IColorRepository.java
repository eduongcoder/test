package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Color;

public interface IColorRepository extends JpaRepository<Color, Integer> {

}
