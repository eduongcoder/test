package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer>{

}
