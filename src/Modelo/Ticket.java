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
    private Time horaVenta; 
    private String nombreObra;
    private Date fechaVenta;
    private ArrayList <String> boletosVendidos;
    private double totalVenta;
    private double montoEntregado;
    private double cambio;

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

    public Time getHoraVenta() {
        return horaVenta; 
    }

    public void setHoraVenta(Time horaVenta) {
        this.horaVenta = horaVenta; 
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public void obtenerCambio(){
        this.cambio = this.montoEntregado - this.totalVenta;  
    }
    
    
    
    
    
}
