package com.stacktrace.Stacktrace.service;

import java.util.ArrayList;
import java.util.List;

import com.stacktrace.Stacktrace.model.Billetera;
import com.stacktrace.Stacktrace.model.Usuario;
import com.stacktrace.Stacktrace.repository.IUsuarioRepository;
import com.stacktrace.Stacktrace.repository.QueryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UsuarioService {
    @Autowired
    IUsuarioRepository iUsuarioRepository;

    @Autowired
    BilleteraService billeteraService;

    @Autowired
    QueryRepository queryRepository;

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        iUsuarioRepository.findAll().forEach(usuarios1 -> usuarios.add(usuarios1));
        return usuarios;
    }

    // Burca un usuario por el id y sus billeteras asociadas
    public String getUsuarioById(Long userId) {
        if (!iUsuarioRepository.findById(userId).isEmpty()) {
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append(iUsuarioRepository.findById(userId).get().toString());
            List<Billetera> billeteras = billeteraService.getBilleteraByUserId(userId);
            if (billeteras != null && !billeteras.isEmpty()) {
                json.append(", \"billeteras\": [" + billeteras.get(0).toString());
                if (billeteras.size() > 1) {
                    for (int i = 1; i < billeteras.size(); i++) {
                        json.append(", " + billeteras.get(i).toString());
                    }

                }
                json.append("]");

            }
            json.append("}");
            return json.toString();
        }

        else
            return null;
    }

    // Regisrar un nuevo usuario con la contraseña encriptada, además se crea una
    // billetera con saldo 0.
    public String registrarUsuario(Usuario usuario) {
        usuario.toUpperCase();
        if (queryRepository.findByEmail(usuario.getEmail()) == null) {
            //Con Argon2 encripto la contraseña
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
            usuario.setPassword(hash);
            Usuario nuevo = iUsuarioRepository.save(usuario);
            return "{" + nuevo.toString() + ", \"billetera\": ["
                    + billeteraService.registrarBilletera(nuevo.getUserId()) + "]}";

        } else
            return null;

    }

    // Actualizar un usuario existente
    public String update(Usuario usuario) {
        usuario.toUpperCase();
        if (queryRepository.findByEmail(usuario.getEmail()) != null) {
            usuario.setUserId(queryRepository.findByEmail(usuario.getEmail()).getUserId());
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
            usuario.setPassword(hash);
            return "{" + iUsuarioRepository.save(usuario).toString() + "}";
        } else
            return null;
    }

    // Borra un usuario y actualiza las billeteras huerfanas con el valor -1 en el
    // campo userId
    public boolean deleteUsuario(Long id) {
        if (!iUsuarioRepository.findById(id).isEmpty()) {
            iUsuarioRepository.deleteById(id);
            List<Billetera> billeterasHuerfanas = billeteraService.getBilleteraByUserId(id);
            if (!billeterasHuerfanas.isEmpty()) {
                for (int i = 0; i < billeterasHuerfanas.size(); i++) {
                    billeterasHuerfanas.get(i).setUserId(-1L);
                    billeteraService.update(billeterasHuerfanas.get(i));
                }
            }
            return true;
        }else return false;
    }

}
