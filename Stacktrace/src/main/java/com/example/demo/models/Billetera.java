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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "billeteras")
public class Billetera {

	public Billetera() {
	}

	public Billetera(float saldoTotal, Usuario usuario) {
		this.saldoTotal = saldoTotal;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billeteraid")
	private Long billeteraId;

	@Schema(hidden = true)
	@Column(name = "saldototal")
	private float saldoTotal;

	@Schema(hidden = true)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "userid")
	private Usuario usuario;

	@Schema(hidden = true)
	@OneToMany(mappedBy = "billetera", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
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

	@JsonIgnore
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setSaldoTotal(float saldoTotal) {
		this.saldoTotal += saldoTotal;
	}

}
