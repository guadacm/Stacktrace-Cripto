package com.example.demo.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {

}
