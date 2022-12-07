package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

// Falta agregar el campo de duracion en la ventana de agregar funcion
public class Funcion {
    private int id;
    private Obra obra;
    private Date fechaPresentacion;
    private Time horaPresentacion;
    private ArrayList<Integer> asientos; 
    private final double precioA = 0.2;
    private final double precioB = 0.1;

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

    public void setAsientos(ArrayList<Integer> asientos){
        this.asientos = asientos; 
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

    public ArrayList<Integer> getAsientos() {
        return asientos;
    }
    
    public double getPrecioA() {
        return precioA;
    }

    public double getPrecioB() {
        return precioB;
    }

    
    
}
