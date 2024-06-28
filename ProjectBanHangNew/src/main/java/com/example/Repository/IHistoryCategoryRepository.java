package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.HistoryCategory;

public interface IHistoryCategoryRepository extends JpaRepository<HistoryCategory, Integer>{

}
