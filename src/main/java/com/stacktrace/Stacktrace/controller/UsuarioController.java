package com.stacktrace.Stacktrace.controller;

import java.util.List;

import com.stacktrace.Stacktrace.model.Usuario;
import com.stacktrace.Stacktrace.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Eliminar usuario", description = "Ingresar userId. Devuelve true si se pudo eliminar el usuario y false en otro caso.Elimina solamente el usuario, es decir, no elimina las billeteras ni las operaciones que involucren a estas. Las billeteras huerfanas quedan con el valor -1 en el campo userId sin la posibilidad de saber a quien perteneció.")
    @DeleteMapping("/usuario/{userId}")
    public boolean deleteUsuario(
            @Parameter(description = "Id que desea eliminar. Use 1 al 10 para probar. ", required = true, example = "5") @PathVariable("userId") Long id) {
        return usuarioService.deleteUsuario(id);
    }

    @Operation(summary = "Listar usuarios",description = "Devuelve una lista de todos los usuarios que hay en el sistema")
    @GetMapping("/usuario")
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @Operation(summary = "Buscar un usuario", description = "Ingresar userId. Devuelve el usuario con el Id dado o null si no se encuentra")
    @GetMapping(value = "/usuario/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    private String getUsuarioById(
            @Parameter(description = "userId que desea buscar. Use 1 al 10 para probar. ", required = true, example = "5") @PathVariable("userId") Long userId) {
        return usuarioService.getUsuarioById(userId);
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Ingresar datos de usuario en el cuerpo de la peticion. Crea un usuario con los datos recibidos y una billetera por defecto con saldo 0. Si el registro tuvo exito devuelve el usuario registrado, sino devuelve null")
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }) })
    @PostMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @Operation(summary = "Actualizar datos de usuario", description = "Ingresar datos actualizados en el cuerpo de la peticion. Se pueden actualizar todos los datos menos el email. Devuelve el usuario actualizado o null si el usuario no existe")
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }) })
    @PutMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    private String update(@RequestBody Usuario usuario) {
        return usuarioService.update(usuario);
    }
}
