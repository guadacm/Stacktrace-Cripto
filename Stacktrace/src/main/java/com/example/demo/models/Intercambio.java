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

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "intercambios")
public class Intercambio {
	@Schema(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operacionid")
	private Long operacionId;

	@Schema(hidden = true)
	@Column(name = "operacionfechahora")
	private String opFechaHora;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "billeteraidorigen", nullable = false)
	private Billetera bOrigen;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "billeteraiddestino", nullable = false)
	private Billetera bDestino;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "divisatipoorigen", nullable = false)
	private Divisa divOrigen;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "divisatipodestino", nullable = false)
	private Divisa divDestino;

	@Column(name = "monto")
	private float monto; // en pesos

	public Intercambio() {
	}

	@Schema(name = "bOrigen")
	public Billetera getbOrigen() {
		return bOrigen;
	}

	public void setbOrigen(Billetera bOrigen) {
		this.bOrigen = bOrigen;
	}

	@Schema(name = "bDestino")
	public Billetera getbDestino() {
		return bDestino;
	}

	public void setbDestino(Billetera bDestino) {
		this.bDestino = bDestino;
	}

	@Schema(name = "divOrigen")
	public Divisa getDivOrigen() {
		return divOrigen;
	}

	public void setDivOrigen(Divisa divOrigen) {
		this.divOrigen = divOrigen;
	}

	@Schema(name = "divDestino")
	public Divisa getDivDestino() {
		return divDestino;
	}

	public void setDivDestino(Divisa divDestino) {
		this.divDestino = divDestino;
	}

	@Schema(example = "5", description = "")
	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Long getOperacionId() {
		return operacionId;
	}

	public void setOperacionId(Long operacionId) {
		this.operacionId = operacionId;
	}

	public String getOpFechaHora() {
		return opFechaHora;
	}

	public void setOpFechaHora(String opFechaHora) {
		this.opFechaHora = opFechaHora;
	}
	
	public void toUpperCase() {
		this.divOrigen.setDivisaTipo(this.divOrigen.getDivisaTipo().toUpperCase());
		this.divDestino.setDivisaTipo(this.divDestino.getDivisaTipo().toUpperCase());
	}
	
}
