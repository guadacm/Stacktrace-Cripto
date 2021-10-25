package com.example.demo.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("FROM Usuario WHERE email = ?1")
	List<Usuario> findByEmail(String email);

}
