package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Usuario;
import com.example.demo.repositries.UsuarioRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BilleteraService billeteraService;

	@Override
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = repository.findAll();
		return usuarios;
	}

	@Override
	public Usuario getUsuarioById(Long userId) {
		Optional<Usuario> aux = repository.findById(userId);
		if (!aux.isEmpty()) {
			Usuario usuario = aux.get();
			return usuario;
		} else
			return null;
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		usuario.toUpperCase();
		if (repository.findByEmail(usuario.getEmail()).isEmpty()) {
			// Con Argon2 encripto la contraseña
			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
			String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
			usuario.setPassword(hash);
			Usuario nuevo = repository.save(usuario);
			// Creo una billetera por defecto
			billeteraService.registrarBilletera(nuevo.getUserId());
			nuevo.setBilleteras(billeteraService.getBilleteraByUserId(nuevo.getUserId()));			
			return nuevo;
		} else
			return null;
	}

	@Override
	public Usuario update(Usuario usuario) {
		usuario.toUpperCase();
		List<Usuario> aux = repository.findByEmail(usuario.getEmail());
		if (!aux.isEmpty()) {
			usuario.setUserId(aux.get(0).getUserId());
			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
			String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
			usuario.setPassword(hash);
			return repository.save(usuario);
		} else
			return null;
	}

	@Override
	public boolean deleteUsuario(Long id) {
		if (!repository.findById(id).isEmpty()) {
			repository.deleteById(id);
			return true;
		} else
			return false;
	}

}
