package Controlador; 

import Modelo.*;
import Recursos.*;
import Vista.*;
import java.awt.event.*;

import javax.swing.JOptionPane;
import DAO.DAOUsuario;

public class CtrlCambiosUsuario implements ActionListener {
    private User modelUser = null;
    private CambiosUsuario frmCUsuario;

    public CtrlCambiosUsuario(User modelUser, CambiosUsuario frmCUsuario) {
        this.modelUser = modelUser;
        this.frmCUsuario = frmCUsuario;
        this.frmCUsuario.getBtnBuscar().addActionListener(this);
        this.frmCUsuario.getBtnCancelar().addActionListener(this);
        this.frmCUsuario.getBtnModificar().addActionListener(this);
        this.frmCUsuario.getBtnEliminar().addActionListener(this);
        this.frmCUsuario.getBtnMenu().addActionListener(this);
        this.frmCUsuario.setVisible(true);
    } 

    @Override
    public void actionPerformed(ActionEvent event ){
        if (event.getSource() == this.frmCUsuario.getBtnBuscar()){

            if (this.frmCUsuario.getTxtNombreUser().getText().isEmpty()){
                JOptionPane.showMessageDialog(frmCUsuario, "Por favor ingrese un nombre de usuario", null, 0);
            } else {
                DAOUsuario daoUsuario = new DAOUsuario(); 
                this.modelUser = daoUsuario.buscarUsuario(this.frmCUsuario.getTxtNombreUser().getText()); 
                if (this.modelUser == null){
                    JOptionPane.showMessageDialog(frmCUsuario, "Usuario no encontrado", null, 0);
                } else {
                    mostrarDatos(); 
                }
            }

        } else if (event.getSource() == this.frmCUsuario.getBtnModificar()){

        } else if (event.getSource() == this.frmCUsuario.getBtnEliminar()){
            
        } else if (event.getSource() == this.frmCUsuario.getBtnCancelar()){
            
        } else if (event.getSource() == this.frmCUsuario.getBtnMenu()){
            
        }
    }
}