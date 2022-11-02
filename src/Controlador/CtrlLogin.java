package Controlador; 

import Modelo.*;
import Recursos.*;
import Vista.*;
import java.awt.event.*;

import DAO.DAOUsuario; 

public class CtrlLogin implements ActionListener{
    private User mod; 
    private DAOUsuario modC; 
    private Login frm; 
    
    public CtrlLogin(User mod, DAOUsuario modC, Login frm){
        this.mod = mod; 
        this.modC = modC; 
        this.frm  = frm; 
        this.frm.getBtnIngresar().addActionListener(this);
        this.frm.getTxtContra().addActionListener(this); 
        this.frm.getTxtUsuario().addActionListener(this); 
        this.frm.getTxtWarning().addActionListener(this);
    }

    public void iniciar(){
        frm.setLocationRelativeTo(null);
    }

    @Override 
    public void actionPerformed(ActionEvent e){
        String contrasena = null; 
        if (e.getSource() == frm.getBtnIngresar()){
            mod.setNombreUsuario(frm.getTxtUsuario().getText());
            contrasena = Encrip.ecnode(mod.getNombreUsuario(), String.valueOf(frm.getTxtContra().getPassword()));
            System.out.println(String.valueOf(frm.getTxtContra().getPassword())); 
            mod.setContrasena(contrasena);

            if (modC.isLogin(mod)){
                frm.getTxtWarning().setText("Login exitoso");
            } else {
                frm.getTxtWarning().setText("Usuario o contraseña incorrectos**"); 
            }

            frm.getTxtUsuario().setText(null);
            frm.getTxtContra().setText(null);
        }
    }


}