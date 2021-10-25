package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Usuario;

public interface UsuarioService {
	public List<Usuario> getUsuarios();

	public Usuario getUsuarioById(Long userId);

	public Usuario registrarUsuario(Usuario usuario);

	public Usuario update(Usuario usuario);

	public boolean deleteUsuario(Long id);
}
