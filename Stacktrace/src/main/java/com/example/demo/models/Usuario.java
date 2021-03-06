package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "usuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@Schema(hidden = true)
	private List<Billetera> billeteras;
	
	public List<Billetera> getBilleteras() {
		return billeteras;
	}

	public void setBilleteras(List<Billetera> billeteras) {
		this.billeteras = billeteras;
		for(Billetera txn : billeteras) {
			txn.setUsuario(this);
		}
	}

	public Usuario() {
	}

	public Usuario(int dni, String sexo, String nombre, String apellido, String email, String telefono,
			String password) {
		this.dni = dni;
		this.sexo = sexo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
	}

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

	@Override
	public String toString() {
		return "Usuario [userId=" + userId + ", dni=" + dni + ", sexo=" + sexo + ", nombre=" + nombre + ", apellido="
				+ apellido + ", email=" + email + ", telefono=" + telefono + ", password=" + password + "]";
	}
}
