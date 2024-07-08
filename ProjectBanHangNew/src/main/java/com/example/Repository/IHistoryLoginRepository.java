package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.HistoryLogin;

public interface IHistoryLoginRepository extends JpaRepository<HistoryLogin, Integer>{

}
