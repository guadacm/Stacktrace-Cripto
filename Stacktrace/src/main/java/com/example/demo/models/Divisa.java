package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "divisas")
public class Divisa {

	@Id
	@Column(name = "divisatipo")
	private String divisaTipo;

	@Column(name = "divisavalor")
	private float divisaValor;

	public Divisa() {
	}

	public Divisa(String divisaTipo, float divisaValor) {
		this.divisaTipo = divisaTipo;
		this.divisaValor = divisaValor;
	}

	@Schema(example = "ADA", description = "")
	public String getDivisaTipo() {
		return divisaTipo;
	}

	public void setDivisaTipo(String divisaTipo) {
		this.divisaTipo = divisaTipo;
	}

	@Schema(example = "2146", description = "")
	public float getDivisaValor() {
		return divisaValor;
	}

	public void setDivisaValor(float divisaValor) {
		this.divisaValor = divisaValor;
	}

	public void toUpperCase() {
		this.divisaTipo = this.divisaTipo.toUpperCase();
	}

}
