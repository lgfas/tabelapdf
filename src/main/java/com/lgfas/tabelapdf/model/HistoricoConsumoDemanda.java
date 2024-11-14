package com.lgfas.tabelapdf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class HistoricoConsumoDemanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    private String mes;
    private double demandaMedidaPonta;
    private double demandaMedidaForaPonta;
    private double demandaMedidaReativoExcedente;
    private double consumoFaturadoPontaTot;
    private double consumoFaturadoForaPonta;
    private double consumoFaturadoReativoExcedente;
    private double horarioReservadoConsumo;
    private double horarioReservadoReativoExcedente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getDemandaMedidaPonta() {
        return demandaMedidaPonta;
    }

    public void setDemandaMedidaPonta(double demandaMedidaPonta) {
        this.demandaMedidaPonta = demandaMedidaPonta;
    }

    public double getDemandaMedidaForaPonta() {
        return demandaMedidaForaPonta;
    }

    public void setDemandaMedidaForaPonta(double demandaMedidaForaPonta) {
        this.demandaMedidaForaPonta = demandaMedidaForaPonta;
    }

    public double getDemandaMedidaReativoExcedente() {
        return demandaMedidaReativoExcedente;
    }

    public void setDemandaMedidaReativoExcedente(double demandaMedidaReativoExcedente) {
        this.demandaMedidaReativoExcedente = demandaMedidaReativoExcedente;
    }

    public double getConsumoFaturadoPontaTot() {
        return consumoFaturadoPontaTot;
    }

    public void setConsumoFaturadoPontaTot(double consumoFaturadoPontaTot) {
        this.consumoFaturadoPontaTot = consumoFaturadoPontaTot;
    }

    public double getConsumoFaturadoForaPonta() {
        return consumoFaturadoForaPonta;
    }

    public void setConsumoFaturadoForaPonta(double consumoFaturadoForaPonta) {
        this.consumoFaturadoForaPonta = consumoFaturadoForaPonta;
    }

    public double getConsumoFaturadoReativoExcedente() {
        return consumoFaturadoReativoExcedente;
    }

    public void setConsumoFaturadoReativoExcedente(double consumoFaturadoReativoExcedente) {
        this.consumoFaturadoReativoExcedente = consumoFaturadoReativoExcedente;
    }

    public double getHorarioReservadoConsumo() {
        return horarioReservadoConsumo;
    }

    public void setHorarioReservadoConsumo(double horarioReservadoConsumo) {
        this.horarioReservadoConsumo = horarioReservadoConsumo;
    }

    public double getHorarioReservadoReativoExcedente() {
        return horarioReservadoReativoExcedente;
    }

    public void setHorarioReservadoReativoExcedente(double horarioReservadoReativoExcedente) {
        this.horarioReservadoReativoExcedente = horarioReservadoReativoExcedente;
    }
}

