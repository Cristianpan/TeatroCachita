package Controlador;

import Modelo.Funcion;
import Vista.ElegirFuncion;

public class CtrlEligegirFuncion {
    private ElegirFuncion vista; 
    private Funcion funcion;

    public CtrlEligegirFuncion(ElegirFuncion vista) {
        this.vista = vista;
        this.vista.setVisible(true);
    } 

    
    
}
