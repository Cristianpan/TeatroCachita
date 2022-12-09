package Controlador;

import Vista.MenuAdmi;
import Vista.Register;
import DAO.DAOUsuario;
import excepciones.ExcepcionCamposVacios;
import excepciones.ExcepcionSoloLetras;
import Modelo.*;

import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
            try {
                esVacioInput();
                verificarLetras();
                DAOUsuario daoUsuario = new DAOUsuario();
                if (daoUsuario.buscarUsuario(this.frmRegister.getTxtUsuario().getText().trim()) == null) {

                    obtenerDatosRegistro();
                    limpiarCampos();
                    daoUsuario.isRegister(this.modelUser);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");

                } else {
                    this.frmRegister.getTxtWarning().setText("Nombre de usuario existente. Ingrese otro");
                }

            } catch (ExcepcionCamposVacios exception) {
                JOptionPane.showMessageDialog(this.frmRegister, exception.getMessage());
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null,
                        "Ha ocurrido un error en el sitema.\nPor favor intente nuevemante.");
            } catch (ExcepcionSoloLetras e1) {
                JOptionPane.showMessageDialog(frmRegister, "Los campos Nombre y Apellido solo aceptan letras");
                e1.printStackTrace();
            }
        }

        // boton cancelar
        if (e.getSource() == this.frmRegister.getBtnCancelar()) {
            int opcion = JOptionPane.showConfirmDialog(frmRegister, "¿Desea cancelar la acción?", null,
                    JOptionPane.YES_NO_OPTION, -1);
            if (opcion == 0) {
                limpiarCampos();
            }

        }

        // boton regresar menu
        if (e.getSource() == this.frmRegister.getBtnMenu()) {
            int opcion = JOptionPane.showConfirmDialog(frmRegister, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.frmRegister.setVisible(false);
                this.frmRegister.dispose();
            }
        }
    }

    // Retorna verdadero si algun textField es vacio, falso en caso contrario
    public void esVacioInput() throws ExcepcionCamposVacios {
        if (frmRegister.getTxtNombre().getText().isEmpty()
                || frmRegister.getTxtApellido().getText().isEmpty()
                || frmRegister.getTxtUsuario().getText().isEmpty()
                || frmRegister.getTxtCurp().getText().isEmpty()
                || frmRegister.getTxtContrasena().getText().isEmpty()
                || frmRegister.getBoxTipoUsuario().getSelectedIndex() == 0) {
            throw new ExcepcionCamposVacios("Todos los campos son obligatorios");
        }
        ;
    }
    public void verificarLetras() throws ExcepcionSoloLetras{
        if((!frmRegister.getTxtNombre().getText().matches("[a-zA-Z]*")  || !frmRegister.getTxtApellido().getText().matches("[a-zA-Z]*"))){
            limpiarCampos();
            throw new ExcepcionSoloLetras("Solo se permiten letras");
        }
    }

    // Obtiene todos los datos proporcionados en la ventana de registro de usuario y
    // los agrega al modelUser
    public void obtenerDatosRegistro() {
        modelUser.setNombre(frmRegister.getTxtNombre().getText().trim());
        modelUser.setApellido(frmRegister.getTxtApellido().getText().trim());
        modelUser.setNombreUsuario(frmRegister.getTxtUsuario().getText().trim());
        modelUser.setTipo(frmRegister.getBoxTipoUsuario().getSelectedItem().toString());
        modelUser.setCurp(frmRegister.getTxtCurp().getText().trim());
        modelUser.setContrasena(
                Encrip.ecnode(modelUser.getNombreUsuario(), frmRegister.getTxtContrasena().getText().trim()));
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
