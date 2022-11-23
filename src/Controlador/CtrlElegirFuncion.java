package Controlador;

import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.DAOFuncion;
import Vista.ElegirFuncion;
import Vista.MenuAdmi;
import Modelo.*;

public class CtrlElegirFuncion implements ActionListener{
    Ticket ticket;
    ElegirFuncion vista;
    DAOFuncion dao;

    public CtrlElegirFuncion(ElegirFuncion vista) {
        this.vista = vista;
        dao = new DAOFuncion();

        this.vista.getBtnElegirAsientos().addActionListener(this);
        this.vista.getBtnRegresarMenu().addActionListener(this);
        this.vista.getComboBoxFecha().addActionListener(this);
        this.vista.getjButton1().addActionListener(this); // Boton de cancelar en la vista

        iniciarBoxFechas();

        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        // Escuchar cuando se selecciono un item y traer la fecha y actualizar la
        // ventana conforme esa fecha
        if (this.vista.getComboBoxFecha() == event.getSource()) {
            this.defaultBoxObraHorario();

            // Se captura la fecha del comboBox para hacer la consulta a la base de datos
            String fechaSeleccionadaS = this.vista.getComboBoxFecha().getSelectedItem().toString();
            java.sql.Date sqlDate = null;

            try { // Este parseo se logro con la obra de Dios, sepa como es que salio
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = format.parse(fechaSeleccionadaS);
                sqlDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException exception) {
                System.out.println("Error en el parseo de String a java.util.Date");
            }

            ArrayList<Funcion> funcionesEnFechaSelec = dao.buscarPorFecha(sqlDate);
            this.iniciarBoxObrasPorFecha(funcionesEnFechaSelec);
            this.iniciarBoxHorarioPorFecha(funcionesEnFechaSelec);
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

        // Ir a la ventana elegir asientos / No esta funcionando correctamente por los argumentos del constructor
        if (this.vista.getBtnElegirAsientos() == event.getSource()) {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Seguro desea elegir asientos en este momento?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            
            if (opcion == 0) {
                new CtrlElegirAsientos(ticket, null, null); // Todavia no se le envian los datos completos
                this.vista.setVisible(false);
                this.vista.dispose();
            }        
        }
        
        // Button Cancelar / Cancela la operacion y limpia todos los datos en la ventana
        if (this.vista.getjButton1() == event.getSource()) {
            limpiarVentana(); // Errores al reiniciar el comboBox de fechas
        }
    }
    
    public void iniciarBoxFechas() {
        ArrayList<Funcion> funcionesRegistradas = dao.obtenerFuncionesRegistradas();

        this.vista.getComboBoxFecha().addItem("-Seleccionar fecha-");

        // Agregar las fechas de presentacion de las obras existentes
        funcionesRegistradas.forEach(funcion -> {
            this.vista.getComboBoxFecha().addItem(funcion.getFechaPresentacion().toString());
        });
    }

    public void iniciarBoxObrasPorFecha(ArrayList<Funcion> funcionesEnFechaSelec) {
        this.vista.getComboBoxObra().removeAllItems();
        this.vista.getComboBoxObra().addItem("-Seleccionar Obra-");

        funcionesEnFechaSelec.forEach(funcion -> {
            this.vista.getComboBoxObra().addItem(funcion.getObra().getNombre());
        });
    }
    
    public void iniciarBoxHorarioPorFecha(ArrayList<Funcion> funcionesEnFechaSelec) {
        this.vista.getComboBoxHorario().removeAllItems();
        this.vista.getComboBoxHorario().addItem("Seleccionar Horario");

        funcionesEnFechaSelec.forEach(funcion -> {
            this.vista.getComboBoxHorario().addItem(funcion.getHoraPresentacion().toString());
        });
    }
    
    // Hace que el comboBox de horario y obra regresen a un estado inicial
    public void defaultBoxObraHorario() {
        this.vista.getComboBoxObra().removeAllItems();
        this.vista.getComboBoxHorario().removeAllItems();

        this.vista.getComboBoxObra().addItem("-");
        this.vista.getComboBoxHorario().addItem("-");
    }

    public void limpiarVentana() {
        this.defaultBoxObraHorario();
    }
}
