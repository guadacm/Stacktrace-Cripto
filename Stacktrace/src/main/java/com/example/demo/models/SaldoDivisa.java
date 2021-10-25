package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "saldosdivisas")
public class SaldoDivisa {

	public SaldoDivisa(String divisaTipo, Billetera billetera) {
		this.divisaTipo = divisaTipo;
		this.divisaSaldo = 0;
		this.billetera = billetera;
	}

	public SaldoDivisa() {
	}

	@Schema(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saldosid")
	private Long saldoId;

	@Column(name = "divisatipo")
	private String divisaTipo;

	@Column(name = "divisasaldo")
	private float divisaSaldo;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "billeteraid", nullable = false)
	private Billetera billetera;

	@JsonIgnore
	public Billetera getBilletera() {
		return billetera;
	}

	public void setBilletera(Billetera billetera) {
		this.billetera = billetera;
	}

	@JsonIgnore
	public Long getSaldoId() {
		return saldoId;
	}

	public void setSaldoId(Long saldoId) {
		this.saldoId = saldoId;
	}

	public String getDivisaTipo() {
		return divisaTipo;
	}

	public void setDivisaTipo(String divisaTipo) {
		this.divisaTipo = divisaTipo;
	}

	public float getDivisaSaldo() {
		return divisaSaldo;
	}

	public void setDivisaSaldo(float divisaSaldo) {
		this.divisaSaldo += divisaSaldo;
	}
}