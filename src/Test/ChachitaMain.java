package Test;

import Controlador.CtrlLogin;
import DAO.DAOUsuario;
import Modelo.User;
import Vista.Login;

public class ChachitaMain {
    public static void main(String[] args) {
        Login frmLog = new Login();
        //Register frmRegister = new Register();  
        DAOUsuario consultas = new DAOUsuario(); 
        User user = new User(); 

        //CtrlRegister ctrlRegister = new CtrlRegister(user, consultas, frmRegister); 
        //frmRegister.setVisible(true);

        CtrlLogin ctrl = new CtrlLogin(user, consultas, frmLog);
        frmLog.setVisible(true); 
    }
}
