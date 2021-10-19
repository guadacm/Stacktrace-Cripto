package com.stacktrace.Stacktrace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(name = "dni")
    private int dni;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "password")
    private String password;

    @Schema(example = "1", description = "")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Schema(example = "12345678", description = "")
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Schema(example = "FEMENINO", description = "")
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Schema(example = "MARTA", description = "")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Schema(example = "DOMINGUEZ", description = "")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Schema(example = "MARTA@DOMINGUEZ", description = "")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Schema(example = "2664962356", description = "")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Schema(example = "123456", description = "")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void toUpperCase() {
        this.sexo = this.sexo.toUpperCase();
        this.nombre = this.nombre.toUpperCase();
        this.apellido = this.apellido.toUpperCase();
        this.email = this.email.toUpperCase();
    }

    public String toString() {
        StringBuilder json = new StringBuilder();
        json.append("\"userid\": \"" + this.getUserId() + "\",");
        json.append("\"dni\": \"" + this.getDni() + "\",");
        json.append("\"sexo\": \"" + this.getSexo() + "\",");
        json.append("\"nombre\": \"" + this.getNombre() + "\",");
        json.append("\"apellido\": \"" + this.getApellido() + "\",");
        json.append("\"email\": \"" + this.getEmail() + "\",");
        json.append("\"telefono\": \"" + this.getTelefono() + "\",");
        json.append("\"password\": \"" + this.getPassword() + "\"");
        return json.toString();
    }

}
