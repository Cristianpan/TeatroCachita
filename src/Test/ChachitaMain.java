package Test;

import Controlador.*;
import DAO.DAOFuncion;
import Modelo.Funcion;
import Modelo.Ticket;
import Vista.*;
import java.util.ArrayList;

public class ChachitaMain {
  public static void main(String[] args) {
    // new CtrlLogin(new User(), new Login());

   // new CtrlReportes(new Reportes());
      Funcion funcion = new Funcion();
      DAOFuncion dao= new DAOFuncion();
      ArrayList<Funcion> funciones= dao.obtenerFuncionesRegistradas();
      System.out.println();
      Ticket tick= new Ticket();
      tick.setNombreObra(funciones.get(0).getObra().getNombre());
      ElegirAsientos s= new ElegirAsientos();
      new CtrlElegirAsientos(tick, s, funciones.get(0));
              
  }
}
