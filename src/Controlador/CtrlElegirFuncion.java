package Controlador;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.DAOFuncion;
import Vista.ElegirFuncion;
import Vista.MenuAdmi;
import Modelo.*;

public class CtrlElegirFuncion implements ActionListener {
    Ticket ticket;
    ElegirFuncion vista;
    DAOFuncion dao;

    public CtrlElegirFuncion(Ticket ticket, ElegirFuncion vista) {
        this.ticket = ticket;
        this.vista = vista;

        this.vista.getBtnElegirAsientos().addActionListener(this);
        this.vista.getBtnRegresarMenu().addActionListener(this);
        this.vista.getComboBoxFecha().addActionListener(this);
        this.vista.getjButton1().addActionListener(this); // Boton de cancelar en la vista

        iniciarBoxFechas();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        // Escuchar cuando se selecciono un item y traer la fecha y actualizar la ventana conforme esa fecha
        if (this.vista.getComboBoxFecha() == event.getSource()) {
            
        }

        // Regresar al menu
        if (this.vista.getBtnRegresarMenu() == event.getSource()) {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);

            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.vista.setVisible(false);
                this.vista.dispose();
            }
        }
        
        if (this.vista.getjButton1() == event.getSource()) {
            limpiarVentana(); // No se ha implementado el limpiar ventana
        }
    }
    
    public void iniciarBoxFechas() {
        dao = new DAOFuncion();
        ArrayList<Funcion> funcionesRegistradas = dao.obtenerFuncionesRegistradas();

        this.vista.getComboBoxFecha().addItem("-Seleccionar fecha-");

        // Agregar las fechas de presentacion de las obras existentes
        funcionesRegistradas.forEach(funcion -> {
            this.vista.getComboBoxFecha().addItem(funcion.getFechaPresentacion().toString());
        });
    }
    
    public void limpiarVentana() {
        
    }
    
}
