package com.stacktrace.Stacktrace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "operaciones")
public class Operacion {
    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operacionid")
    private Long operacionId;

    @Schema(hidden = true)
    @Column(name = "operacionfechahora")
    private String opFechaHora;

    @Schema(hidden = true)
    @Column(name = "operaciontipo")
    private String opTipo;

    @Column(name = "billeteraidorigen")
    private long bOrigen;

    @Column(name = "billeteraiddestino")
    private long bDestino;

    @Column(name = "divisatipoorigen")
    private String divOrigen;

    @Column(name = "divisatipodestino")
    private String divDestino;

    @Column(name = "monto")
    private float monto; //se guarda en pesos

    public Operacion() {
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

    public String getOpTipo() {
        return opTipo;
    }

    public void setOpTipo(String opTipo) {
        this.opTipo = opTipo;
    }

    @Schema(name = "bOrigen", example = "7", description = "")
    public long getbOrigen() {
        return bOrigen;
    }

    public void setbOrigen(Long bOrigen) {
        this.bOrigen = bOrigen;
    }

    @Schema(name = "bDestino", example = "11", description = "")
    public long getbDestino() {
        return bDestino;
    }

    public void setbDestino(Long bDestino) {
        this.bDestino = bDestino;
    }

    @Schema(example = "ETH", description = "")
    public String getDivOrigen() {
        return divOrigen;
    }

    public void setDivOrigen(String divOrigen) {
        this.divOrigen = divOrigen;
    }
    @Schema(example = "BTC", description = "")
    public String getDivDestino() {
        return divDestino;
    }

    public void setDivDestino(String divDestino) {
        this.divDestino = divDestino;
    }
    
    public void toUpperCase() {
        //this.opFechaHora = this.opFechaHora.toUpperCase();
        //this.opTipo = this.opTipo.toUpperCase();
        this.divOrigen = this.divOrigen.toUpperCase();
        this.divDestino = this.divDestino.toUpperCase();
    }

    /*public String toString() {
        StringBuilder json = new StringBuilder();
        json.append("\"opFechaHora\": \"" + this.getOpFechaHora() + "\",");
        json.append("\"opTipo\": \"" + this.getOpTipo() + "\",");
        json.append("\"bOrigen\": \"" + this.getbOrigen() + "\",");
        json.append("\"bDestino\": \"" + this.getbDestino() + "\",");
        json.append("\"divOrigen\": \"" + this.getDivOrigen() + "\",");
        json.append("\"divDestino\": \"" + this.getDivDestino() + "\",");
        json.append("\"monto\": \"" + this.getMonto() + "\"");
        return json.toString();
    }*/
}
