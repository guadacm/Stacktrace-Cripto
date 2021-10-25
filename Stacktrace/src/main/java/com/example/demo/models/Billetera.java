package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "billeteras")
public class Billetera {

	public Billetera() {
	}

	public Billetera(float saldoTotal, float pesos, Usuario usuario) {
		super();
		this.saldoTotal = saldoTotal;
		this.pesos = pesos;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billeteraid")
	private Long billeteraId;

	@Column(name = "saldototal")
	private float saldoTotal;

	@Column(name = "pesos")
	private float pesos;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "userid")
	private Usuario usuario;

	@Transient
	private List<SaldoDivisa> saldoDivisa;

	public List<SaldoDivisa> getSaldoDivisa() {
		return saldoDivisa;
	}

	public void setSaldoDivisa(List<SaldoDivisa> saldoDivisa) {
		this.saldoDivisa = saldoDivisa;
		for (SaldoDivisa txn : saldoDivisa) {
			txn.setBilletera(this);
		}
		this.saldoDivisa = saldoDivisa;
	}

	@Schema(example = "150.0", description = "")
	public float getPesos() {
		return pesos;
	}

	public void setPesos(float pesos) {
		this.pesos += pesos;
	}

	@Schema(example = "1", description = "")
	public Long getBilleteraId() {
		return billeteraId;
	}

	public void setBilleteraId(Long billeteraId) {
		this.billeteraId = billeteraId;
	}

	@Schema(example = "300.0", description = "")
	public float getSaldoTotal() {
		return saldoTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setSaldoTotal(float saldoTotal) {
		this.saldoTotal += saldoTotal;
	}

	@Override
	public String toString() {
		return "Billetera [billeteraId=" + billeteraId + ", saldoTotal=" + saldoTotal + ", pesos=" + pesos + "]";
	}
}