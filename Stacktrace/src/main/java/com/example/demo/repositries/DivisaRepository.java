package com.example.demo.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Divisa;

public interface DivisaRepository extends JpaRepository<Divisa, String> {

}
