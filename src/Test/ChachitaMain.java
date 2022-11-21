package Test;

import Controlador.*;
import DAO.DAOFuncion;
import Modelo.Funcion;
import Modelo.Ticket;
import Modelo.User;
import Vista.*;
import java.util.ArrayList;

public class ChachitaMain {
    public static void main(String[] args) {
     //  new CtrlLogin(new User(), new Login());  
      
      
      ElegirAsientos a= new ElegirAsientos();
      
      DAOFuncion daoF = new DAOFuncion();
        ArrayList<Funcion> funciones = daoF.obtenerFuncionesRegistradas();
        Funcion funcion = funciones.get(1);
        
      Ticket ticket = new Ticket();
      ticket.setNombreObra(funcion.getObra().getNombre());
      CtrlElegirAsientos b= new CtrlElegirAsientos(ticket, a, funcion);
      
    }
}
