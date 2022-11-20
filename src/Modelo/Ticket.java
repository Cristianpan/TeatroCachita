/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author diana
 */
public class Ticket {
    //nombre ya está por defecto
    private int numVenta;
    private Time horario;
    private String nombreObra;
    private Date fechaVenta;
    private ArrayList <String> boletosVendidos;
    private double totalVenta;
    private double montoEntregado;
    private double cambio;

    public Ticket() {
    }

    //get y set
    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public ArrayList<String> getBoletosVendidos() {
        return boletosVendidos;
    }

    public void setBoletosVendidos(ArrayList<String> boletosVendidos) {
        this.boletosVendidos = boletosVendidos;
    }

    
    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public double getMontoEntregado() {
        return montoEntregado;
    }

    public void setMontoEntregado(double montoEntregado) {
        this.montoEntregado = montoEntregado;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }
    
    
    
    
    
}
