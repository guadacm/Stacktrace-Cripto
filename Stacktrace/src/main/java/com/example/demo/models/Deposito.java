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
@Table(name = "depositos")
public class Deposito {
	@Schema(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operacionid")
	private Long operacionId;

	@Schema(hidden = true)
	@Column(name = "operacionfechahora")
	private String opFechaHora;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "billeteraiddestino", nullable = false)
	private Billetera bDestino;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "divisatipodestino", nullable = false)
	private Divisa divDestino;
	
	@Column(name = "monto")
	private float monto; // en pesos
	
	public Deposito() {
	}
	
	@Schema(name = "bDestino")
	public Billetera getbDestino() {
		return bDestino;
	}


	public void setbDestino(Billetera bDestino) {
		this.bDestino = bDestino;
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

}
