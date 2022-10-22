package Test;

import Controlador.CtrlLogin;
import Controlador.CtrlRegister;
import Modelo.ConsultaUsuario;
import Modelo.User;
import Recursos.Encrip;
import Vista.Login;
import Vista.Register;

public class ChachitaMain {
    public static void main(String[] args) {
        Login frmLog = new Login();
        //Register frmRegister = new Register();  
        ConsultaUsuario consultas = new ConsultaUsuario(); 
        User user = new User(); 

        //CtrlRegister ctrlRegister = new CtrlRegister(user, consultas, frmRegister); 
        //frmRegister.setVisible(true);

        CtrlLogin ctrl = new CtrlLogin(user, consultas, frmLog);
        frmLog.setVisible(true); 
    }
}
