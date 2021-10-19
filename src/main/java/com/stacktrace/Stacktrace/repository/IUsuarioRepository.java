package com.stacktrace.Stacktrace.repository;

import com.stacktrace.Stacktrace.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

}
