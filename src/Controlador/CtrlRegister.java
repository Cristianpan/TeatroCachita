package Controlador;

import Modelo.*;
import Vista.Register;
import java.awt.event.*;

import javax.swing.JOptionPane;

import Recursos.*;

public class CtrlRegister implements ActionListener {
    private User mod; 
    private ConsultaUsuario modC; 
    private Register frm; 
    
    public CtrlRegister(User mod, ConsultaUsuario modC, Register frm){
        this.mod = mod; 
        this.modC = modC; 
        this.frm  = frm; 
        frm.getBtnCancelar().addActionListener(this);
        frm.getTxtWarning().addActionListener(this);
        frm.getBtnRegistrar().addActionListener(this);

    }

    public void iniciar(){
        frm.setTitle("Register");
        frm.setLocationRelativeTo(null);
    }

    @Override 
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == frm.getBtnRegistrar()){
            if(modC.buscarUsuario(frm.getTxtUsuario().getText()) == null){
                mod.setNombre(frm.getTxtNombre().getText());
                mod.setApellido(frm.getTxtApellido().getText());
                mod.setNombreUsuario(frm.getTxtUsuario().getText());
                mod.setTipo(frm.getBoxTipoUsuario().getSelectedItem().toString());
                mod.setCurp(frm.getTxtCurp().getText());
                mod.setContrasena(Encrip.ecnode(mod.getNombreUsuario(), frm.getTxtContrasena().getText()));
                if (modC.isRegister(mod)){
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                }

                limpiarCampos(); 
            } else {
                frm.getTxtWarning().setText("Usuario existente. Ingrese otro");
            }
        } else if(e.getSource() == frm.getBtnCancelar()){
            limpiarCampos(); 
        }
    }

    public void limpiarCampos(){
        frm.getTxtNombre().setText(null);
        frm.getTxtApellido().setText(null);
        frm.getTxtUsuario().setText(null);
        frm.getTxtContrasena().setText(null);
        frm.getTxtCurp().setText(null);
    }

}
