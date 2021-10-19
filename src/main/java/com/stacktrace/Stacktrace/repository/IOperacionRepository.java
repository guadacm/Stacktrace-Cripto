package com.stacktrace.Stacktrace.repository;

import com.stacktrace.Stacktrace.model.Operacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOperacionRepository extends JpaRepository<Operacion, Long>{
    
}
