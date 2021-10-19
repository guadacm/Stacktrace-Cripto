package com.stacktrace.Stacktrace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "saldosdivisas")
public class SaldoDivisa {

    public SaldoDivisa(Long billeteraId, String divisaTipo) {
        this.billeteraId = billeteraId;
        this.divisaTipo = divisaTipo;
        this.divisaSaldo = 0;
    }

    public SaldoDivisa() {
    }

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saldosid")
    private Long saldoId;

    @Column(name = "billeteraid")
    private Long billeteraId;

    @Column(name = "divisatipo")
    private String divisaTipo;

    @Column(name = "divisasaldo")
    private float divisaSaldo;

    public Long getSaldoId() {
        return saldoId;
    }

    public void setSaldoId(Long saldoId) {
        this.saldoId = saldoId;
    }

    public Long getBilleteraId() {
        return billeteraId;
    }

    public void setBilleteraId(Long billeteraId) {
        this.billeteraId = billeteraId;
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

    public String toString() {
        StringBuilder json = new StringBuilder();
        json.append("{\"" + this.getDivisaTipo() + "\": \""+this.getDivisaSaldo()+"\"}");
        return json.toString();
    }
}
