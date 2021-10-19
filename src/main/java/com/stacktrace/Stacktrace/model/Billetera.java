package com.stacktrace.Stacktrace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="billeteras")
public class Billetera {

    public Billetera() {
    }

    public Billetera(Long userId) {
        this.userId = userId;
        this.saldoTotal=0;
        this.pesos=0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billeteraid")
    private Long billeteraId;
    
    @Column(name="userid")
    private Long userId;
    
    @Column(name="saldototal")
    private float saldoTotal;

    @Column(name = "pesos")
    private float pesos;

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
    @Schema(example = "1", description = "")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Schema(example = "300.0", description = "")
    public float getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(float saldoTotal) {
        this.saldoTotal += saldoTotal;
    }

    public String toString() {
		StringBuilder json = new StringBuilder();
        json.append("{\"id\": \""+this.getBilleteraId()+"\",");
        //json.append("\"userId\": \""+this.getUserId()+"\",");
        json.append("\"saldoTotal\": \""+this.getSaldoTotal()+"\"}");
		return json.toString();
	}

    public String toStringUserId() {
		StringBuilder json = new StringBuilder();
        json.append("{\"id\": \""+this.getBilleteraId()+"\",");
        json.append("\"userId\": \""+this.getUserId()+"\",");
        json.append("\"saldoTotal\": \""+this.getSaldoTotal()+"\",");
        json.append("\"divisas\": [{\"pesos\": \""+this.getPesos()+"\"}");
		return json.toString();
	}
}
