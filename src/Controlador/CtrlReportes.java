package Controlador;

import Vista.Reportes;

public class CtrlReportes {
    private Reportes vista;

    public CtrlReportes(Reportes vista) {
        this.vista = vista;
        this.vista.setVisible(true);
    }    
}
