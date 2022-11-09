package Controlador;

import Modelo.*;
import Vista.MenuAdmi;
import Vista.Register;
import java.awt.event.*;

import javax.swing.JOptionPane;

import DAO.DAOUsuario;
import Recursos.*;

public class CtrlRegister implements ActionListener {
    private User modelUser;
    private Register frmRegister;

    public CtrlRegister(User modelUser, Register frmRegister) {
        this.modelUser = modelUser;
        this.frmRegister = frmRegister;
        this.frmRegister.getBtnCancelar().addActionListener(this);
        this.frmRegister.getTxtWarning().addActionListener(this);
        this.frmRegister.getBtnRegistrar().addActionListener(this);
        this.frmRegister.getBtnMenu().addActionListener(this);
        this.frmRegister.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.frmRegister.getBtnRegistrar()) {
            if (esVacioInput()) {
                JOptionPane.showMessageDialog(this.frmRegister, "Todos los campos son obligatorios");
            } else {
                DAOUsuario daoUsuario = new DAOUsuario();
                if (daoUsuario.buscarUsuario(this.frmRegister.getTxtUsuario().getText()) == null) {

                    obtenerDatosRegistro();
                    limpiarCampos();

                    if (daoUsuario.isRegister(this.modelUser))
                        JOptionPane.showMessageDialog(null, "Registro exitoso");
                    else
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");

                } else {
                    this.frmRegister.getTxtWarning().setText("Usuario existente. Ingrese otro");
                }
            }
        } else if (e.getSource() == this.frmRegister.getBtnCancelar()) {
            limpiarCampos();

        } else if (e.getSource() == this.frmRegister.getBtnMenu()) {
            new CtrlMenu(new MenuAdmi());
            this.frmRegister.setVisible(false);
            this.frmRegister.dispose();
        }
    }

    // Retorna verdadero si algun textField es vacio, falso en caso contrario
    public boolean esVacioInput() {
        return (frmRegister.getTxtNombre().getText().isEmpty() || frmRegister.getTxtApellido().getText().isEmpty()
                || frmRegister.getTxtUsuario().getText().isEmpty() || frmRegister.getTxtCurp().getText().isEmpty()
                || frmRegister.getTxtContrasena().getText().isEmpty()
                || frmRegister.getBoxTipoUsuario().getSelectedIndex() == 0);
    }

    // Obtiene todos los datos proporcionados en la ventana de registro de usuario y
    // los agrega al modelUser
    public void obtenerDatosRegistro() {
        modelUser.setNombre(frmRegister.getTxtNombre().getText());
        modelUser.setApellido(frmRegister.getTxtApellido().getText());
        modelUser.setNombreUsuario(frmRegister.getTxtUsuario().getText());
        modelUser.setTipo(frmRegister.getBoxTipoUsuario().getSelectedItem().toString());
        modelUser.setCurp(frmRegister.getTxtCurp().getText());
        modelUser.setContrasena(Encrip.ecnode(modelUser.getNombreUsuario(), frmRegister.getTxtContrasena().getText()));
    }

    // Limpia todos los textFields tras un registro exitoso o no
    public void limpiarCampos() {
        frmRegister.getTxtNombre().setText(null);
        frmRegister.getTxtApellido().setText(null);
        frmRegister.getTxtUsuario().setText(null);
        frmRegister.getTxtContrasena().setText(null);
        frmRegister.getTxtCurp().setText(null);
        frmRegister.getBoxTipoUsuario().setSelectedIndex(0);
    }

}
