package Test;

import Controlador.*;
import Vista.*;

public class ChachitaMain {
    public static void main(String[] args) {
        CrearFuncion vista = new CrearFuncion();
        new CtrlAgregarFunciones(vista);

        vista.setVisible(true);
    }
}
