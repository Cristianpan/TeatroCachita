package Test;


import Controlador.*;
import Modelo.*;
import Vista.*;

public class ChachitaMain {
    public static void main(String[] args) {
      new CtrlLogin(new User(), new Login());  
    }
}
