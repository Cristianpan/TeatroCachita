package Modelo;

import java.sql.Date;
import java.sql.Time;

// Falta agregar el campo de duracion en la ventana de agregar funcion
public class Funcion {
    private int id;
    private String obraTeatral;
    private Date fechaPresentacion;
    private Time horaPresentacion;
    private boolean[] AsientoA;
    private boolean[] AsientoB;
    private boolean[] AsientoC;

    public void setId(int id) {
        this.id = id;
    }

    public void setObraTeatral(String obraTeatral) {
        this.obraTeatral = obraTeatral;
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

    public String getObraTeatral() {
        return obraTeatral;
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
}
