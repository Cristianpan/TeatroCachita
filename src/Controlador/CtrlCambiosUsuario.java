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
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.frmCUsuario.getBtnBuscar()) {

            if (this.frmCUsuario.getTxtNombreUser().getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmCUsuario, "Por favor ingrese un nombre de usuario", null, 0);
            } else {
                DAOUsuario daoUsuario = new DAOUsuario();
                this.modelUser = daoUsuario.buscarUsuario(this.frmCUsuario.getTxtNombreUser().getText().trim());
                if (this.modelUser == null) {
                    JOptionPane.showMessageDialog(frmCUsuario, "Usuario no encontrado", null, 0);
                } else {
                    mostrarDatos();
                }
            }

        } else if (event.getSource() == this.frmCUsuario.getBtnModificar()) {
            if (modelUser == null) {
                JOptionPane.showMessageDialog(frmCUsuario, "Busque un usuario existente", null, 0);
            } else {
                if (esVacioInput()) {
                    JOptionPane.showMessageDialog(frmCUsuario, "Todos los campos son obligatorios");
                } else {
                    obtenerDatos();
                    DAOUsuario daoUsuario = new DAOUsuario();

                    if (daoUsuario.buscarUsuario(modelUser.getNombreUsuario()) == null || modelUser.getNombreUsuario()
                            .equals(this.frmCUsuario.getTxtNombreUser().getText().trim())) {
                        if (daoUsuario.actualizarUsuario(modelUser)) {
                            JOptionPane.showMessageDialog(frmCUsuario, "Actualización exitosa");
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(frmCUsuario, "Algo ha salido mal");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frmCUsuario, "Nombre de usuario existente. Ingrese otro");
                    }

                }
            }
        } else if (event.getSource() == this.frmCUsuario.getBtnEliminar()) {
            if (modelUser == null) {
                JOptionPane.showMessageDialog(frmCUsuario, "Busque un usuario existente", null, 0);
            } else {
                int opcion = JOptionPane.showConfirmDialog(frmCUsuario,
                        "¿Desea eliminar al usuario " + modelUser.getNombreUsuario() + "?", null,
                        JOptionPane.YES_NO_OPTION, 2);

                if (opcion == 0) {
                    DAOUsuario daoUsuario = new DAOUsuario();
                    if (daoUsuario.eliminarUsuario(this.modelUser.getId())) {
                        JOptionPane.showMessageDialog(frmCUsuario, "Usuario Eliminado");
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(frmCUsuario, "Algo ha salido mal");
                    }
                }
            }

        } else if (event.getSource() == this.frmCUsuario.getBtnCancelar()) {
            int opcion = JOptionPane.showConfirmDialog(frmCUsuario, "¿Desea cancelar la acción?", null,
                    JOptionPane.YES_NO_OPTION, -1);
            if (opcion == 0) {
                limpiarCampos();
            }

        } else if (event.getSource() == this.frmCUsuario.getBtnMenu()) {
            int opcion = JOptionPane.showConfirmDialog(frmCUsuario, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.frmCUsuario.setVisible(false);
                this.frmCUsuario.dispose();
            }

        }
    }

    // Muestra en el frame los datos obtenidos tras haber realizado la busqueda del
    // usuario
    public void mostrarDatos() {
        this.frmCUsuario.getTxtNombre().setText(this.modelUser.getNombre());
        this.frmCUsuario.getTxtApellido().setText(this.modelUser.getApellido());
        this.frmCUsuario.getTxtCurp().setText(this.modelUser.getCurp());
        this.frmCUsuario.getTxtUsuario().setText(this.modelUser.getNombreUsuario());
        this.frmCUsuario.getTxtContrasena()
                .setText(Encrip.deecnode(this.modelUser.getNombreUsuario(), this.modelUser.getContrasena()));
        this.frmCUsuario.getTxtContrasena().setEchoChar((char) 0);
        this.frmCUsuario.getBoxTipoUsuario().setSelectedItem(this.modelUser.getTipo());
    }

    // Retorna true si algun campo está vacio, false en caso contrario
    public boolean esVacioInput() {
        return (this.frmCUsuario.getTxtNombre().getText().isEmpty()
                || this.frmCUsuario.getTxtApellido().getText().isEmpty()
                || this.frmCUsuario.getTxtUsuario().getText().isEmpty()
                || this.frmCUsuario.getTxtCurp().getText().isEmpty()
                || this.frmCUsuario.getTxtContrasena().getText().isEmpty()
                || this.frmCUsuario.getBoxTipoUsuario().getSelectedIndex() == 0);
    }

    // Agrega los datos de los campos en el modelo de Usuario
    public void obtenerDatos() {
        modelUser.setNombre(this.frmCUsuario.getTxtNombre().getText().trim());
        modelUser.setApellido(this.frmCUsuario.getTxtApellido().getText().trim());
        modelUser.setNombreUsuario(this.frmCUsuario.getTxtUsuario().getText().trim());
        modelUser.setCurp(this.frmCUsuario.getTxtCurp().getText().trim());
        modelUser.setContrasena(
                Encrip.ecnode(this.modelUser.getNombreUsuario(), this.frmCUsuario.getTxtContrasena().getText().trim()));
        modelUser.setTipo(this.frmCUsuario.getBoxTipoUsuario().getSelectedItem().toString());
    }

    public void limpiarCampos() {
        this.frmCUsuario.getTxtNombreUser().setText(null);
        this.frmCUsuario.getTxtNombre().setText(null);
        this.frmCUsuario.getTxtApellido().setText(null);
        this.frmCUsuario.getTxtContrasena().setText(null);
        this.frmCUsuario.getTxtCurp().setText(null);
        this.frmCUsuario.getTxtUsuario().setText(null);
        this.frmCUsuario.getBoxTipoUsuario().setSelectedIndex(0);
        this.modelUser = null;
    }
}