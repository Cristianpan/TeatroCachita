package Modelo;

import java.sql.Date;
import java.sql.Time;

// Falta agregar el campo de duracion en la ventana de agregar funcion
public class Funcion {
    private int id;
    private Obra obra;
    private Date fechaPresentacion;
    private Time horaPresentacion;
    private boolean[] AsientoA;
    private boolean[] AsientoB;
    private boolean[] AsientoC;
    private double precioA;
    private double precioB;

    public Funcion() {
        this.precioB= 0.1;
        this.precioA= 0.2;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setObra(Obra obra) {
        this.obra = obra; 
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public void setHoraPresentacion(Time horaPresentacion) {
        this.horaPresentacion = horaPresentacion;
    }

    public void setAsientoA(boolean[] asientoA) {
        AsientoA = asientoA;
    }

    public void setAsientoB(boolean[] asientoB) {
        AsientoB = asientoB;
    }

    public void setAsientoC(boolean[] asientoC) {
        AsientoC = asientoC;
    }

    public int getId() {
        return id;
    }

    public Obra getObra() {
        return obra;
    }

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public Time getHoraPresentacion() {
        return horaPresentacion;
    }

    public boolean[] getAsientoA() {
        return AsientoA;
    }

    public boolean[] getAsientoB() {
        return AsientoB;
    }

    public boolean[] getAsientoC() {
        return AsientoC;
    }

    public double getPrecioA() {
        return precioA;
    }

    public void setPrecioA(double precioA) {
        this.precioA = precioA;
    }

    public double getPrecioB() {
        return precioB;
    }

    public void setPrecioB(double precioB) {
        this.precioB = precioB;
    }
    
    
}
