package Controlador;

import Recursos.*;
import Vista.*;
import DAO.DAOUsuario;
import Modelo.*;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class CtrlLogin implements ActionListener {
    private User modelUser;
    private Login frmLogin;

    public CtrlLogin(User modUser, Login frmLogin) {
        this.modelUser = modUser;
        this.frmLogin = frmLogin;
        this.frmLogin.getBtnIngresar().addActionListener(this);
        this.frmLogin.getTxtContra().addActionListener(this);
        this.frmLogin.getTxtUsuario().addActionListener(this);
        this.frmLogin.getTxtWarning().addActionListener(this);
        this.frmLogin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == frmLogin.getBtnIngresar()) {
            if (frmLogin.getTxtUsuario().getText().isEmpty() || String.valueOf(frmLogin.getTxtContra().getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(frmLogin, "Todos los campos son obligatorios");
            } else {
                obtenerDatos();

                DAOUsuario daoUsuario = new DAOUsuario();
                if (daoUsuario.isLogin(modelUser)) {
                    if (modelUser.getTipo().equals("Administrador")) {
                        cerrarVentana();
                        new CtrlMenu(new MenuAdmi(), "administrador");
                    } else {
                        cerrarVentana();
                        new CtrlElegirFuncion(new ElegirFuncion(), "operador"); 
                    }
                } else {
                    frmLogin.getTxtWarning().setText("Usuario o contraseña incorrectos");
                }
            }
        }
    }

    /*
     * Captura los datos en el textField de Usuario y contraseña,
     * enviandoselo al modelUser a través del setter correspondiente
     */
    public void obtenerDatos() {
        String contrasena = null;
        modelUser.setNombreUsuario(frmLogin.getTxtUsuario().getText().trim());
        contrasena = Encrip.ecnode(modelUser.getNombreUsuario(), String.valueOf(frmLogin.getTxtContra().getPassword()).trim());
        modelUser.setContrasena(contrasena);
    }

    /*
     * Limpia los campos de usuario y contraseña después de
     * apretar el boton de ingresar
     */
    public void limpiarCampos() {
        frmLogin.getTxtUsuario().setText(null);
        frmLogin.getTxtContra().setText(null);
    }

    public void cerrarVentana() {
        frmLogin.setVisible(false);
        frmLogin.dispose();
    }

}