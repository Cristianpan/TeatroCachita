/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * 
 */
public class Obra {
    private int id;
    private String nombre;
    private String genero;
    private String resumen;
    private int duracion;
    private String primerActor;
    private String segundoActor;
    private double precioBoleto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPrimerActor() {
        return primerActor;
    }

    public void setPrimerActor(String primerActor) {
        this.primerActor = primerActor;
    }

    public String getSegundoActor() {
        return segundoActor;
    }

    public void setSegundoActor(String segundoActor) {
        this.segundoActor = segundoActor;
    }

    public double getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(double precioBoleto) {
        this.precioBoleto = precioBoleto;
    }
    
    
}
