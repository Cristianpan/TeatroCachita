package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CtrlMenu implements ActionListener {
    private MenuAdmi menu;
    private PanelMenu panelMenu = new PanelMenu();
    private int indexBoton;

    public CtrlMenu(MenuAdmi menu) {
        this.menu = menu;

        this.menu.getBtnUsuarios().addActionListener(this);
        this.menu.getBtnObras().addActionListener(this);
        this.menu.getBtnFunciones().addActionListener(this);
        this.menu.getBtnReportes().addActionListener(this);
        this.menu.getBtnSalir().addActionListener(this);
        this.panelMenu.getBtnAgregar().addActionListener(this);
        this.panelMenu.getBtnModificar().addActionListener(this);
    
        this.menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Color colorBotonSeleccionado = new Color(255, 102, 0);
        Color colorBotonNormal = new Color(32, 58, 74);

        if (event.getSource() == this.menu.getBtnUsuarios())
            indexBoton = 1;
        else if (event.getSource() == this.menu.getBtnObras())
            indexBoton = 2;
        else if (event.getSource() == this.menu.getBtnFunciones())
            indexBoton = 3;

         // boton usaurios => agregar/modificar
        if (indexBoton == 1) {
            agregarPanel();
            marcarBoton(colorBotonSeleccionado, colorBotonNormal, colorBotonNormal);
            
            if (event.getSource() == this.panelMenu.getBtnAgregar()){
                new CtrlRegister(new User(), new Register()); 
                cerrarVentana();

            } else if (event.getSource() == this.panelMenu.getBtnModificar()){
                new CtrlCambiosUsuario(new User(), new CambiosUsuario()); // Aqui hay una variable que falta por igualarse
                cerrarVentana(); 
            }

        } 
         // boton obras => agregar/modificar
        if (indexBoton == 2) {

            agregarPanel();
            marcarBoton(colorBotonNormal, colorBotonSeleccionado, colorBotonNormal);

            if (event.getSource() == this.panelMenu.getBtnAgregar()){
                new CtrlCrearObra(new Obra(), new CrearObra()); 
                cerrarVentana();
            } else if (event.getSource() == this.panelMenu.getBtnModificar()){
                new CtrlCambiosObra(new Obra(), new CambiosObra()); 
                cerrarVentana();
            }
            
        } 
        // boton funciones => agregar/modificar
        if (indexBoton == 3) {
            agregarPanel();
            marcarBoton(colorBotonNormal, colorBotonNormal, colorBotonSeleccionado);

            if (event.getSource() == this.panelMenu.getBtnAgregar()){
                new CtrlAgregarFunciones(new CrearFuncion(), new Funcion()); 
                cerrarVentana();

            } else if (event.getSource() == this.panelMenu.getBtnModificar()){

                new CtrlModificarEliminarFunciones(new CambiosFunciones(), new Funcion()); 
                cerrarVentana();
            }
        } 
        
        //boton reportes
        if (event.getSource() == menu.getBtnReportes()) {
            // IR A LOS REPORTES
            cerrarVentana();
            new CtrlReportes(new Reportes()); 

        } 
        //boton salir 
        if (event.getSource() == menu.getBtnSalir()) {
            int opcion = JOptionPane.showConfirmDialog(menu, "¿Desea cerrar sesión?", null,
                    JOptionPane.YES_NO_OPTION, 1);

            if (opcion == 0){
                Login frmLogin = new Login();
                User modelUser = new User();
                new CtrlLogin(modelUser, frmLogin);
                cerrarVentana();
            }
            
        }

    }

    // Resalta el boton presionado sobre los demás
    public void marcarBoton(Color color1, Color color2, Color color3) {
        this.menu.getBtnUsuarios().setBackground(color1);
        this.menu.getBtnObras().setBackground(color2);
        this.menu.getBtnFunciones().setBackground(color3);
    }

    // Agrega el panel con las opciones de Agregar, Modificar/Eliminar
    public void agregarPanel() {
        this.panelMenu.setSize(190, 110); // 191, 92
        this.panelMenu.setLocation(0, 0);
        this.menu.getPanelSubmenu().removeAll();
        this.menu.getPanelSubmenu().add(this.panelMenu, BorderLayout.CENTER);
        this.menu.getPanelSubmenu().revalidate();
        this.menu.getPanelSubmenu().repaint();
    }

    public void cerrarVentana() {
        this.menu.setVisible(false);
        this.menu.dispose();
    }

}


