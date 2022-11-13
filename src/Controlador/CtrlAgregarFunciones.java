package Controlador;

import java.sql.*;
import java.text.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import DAO.*;
import Modelo.*;
import Vista.CrearFuncion;

public class CtrlAgregarFunciones implements ActionListener {

    private CrearFuncion vista;
    ArrayList<Funcion> funciones;
    private String obraSeleccionada;

    DAOObra daoObra = new DAOObra();
    DAOFuncion daoFuncion = new DAOFuncion();

    public CtrlAgregarFunciones(CrearFuncion vista) {
        this.vista = vista;
        agregarObrasComboBox();

        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnRegresarMenu().addActionListener(this);
        vista.getComboBoxHora().addActionListener(this);
        vista.getComboBoxObra().addActionListener(this);
    }

    public void agregarObrasComboBox() {
        DAOObra daoObras = new DAOObra();
        ArrayList<Obra> obras = daoObras.obtenerObrasRegistradas();
        vista.getComboBoxObra().addItem("-Seleccionar Obra-");

        for (int i = 0; i < obras.size(); i++) {
            vista.getComboBoxObra().addItem(obras.get(i).getNombre());
        }
    }

    public void iniciar() {
        vista.getjDateChooser1().addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
                    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

                    DAOObra daoObra = new DAOObra();
                    DAOFuncion daoFuncion = new DAOFuncion();
            
                    String fecha = dateFormater.format(vista.getjDateChooser1().getDate());
            
                    Obra obra = daoObra.buscarObra(vista.getComboBoxObra().getSelectedItem().toString());
                    ArrayList<Funcion> funciones = daoFuncion.buscarPorFecha(fecha);
            
                    if (funciones.isEmpty()) {
                        vista.getBtnAgregar().setEnabled(true);
                        vista.getComboBoxHora().addItem("18:00");
                        vista.getComboBoxHora().addItem("20:30");
                    }
            
                    if (funciones.size() == 2) {
                        JOptionPane.showMessageDialog(null, "Ambos horarios han sido registrados previamente. Por favor, seleccione otro día.");
                        vista.getBtnAgregar().setEnabled(false);
                    }
                    
                    for (Funcion funcionIt : funciones) {
                        if ((funcionIt.getHoraPresentacion().equals(new Time(18, 0, 0))) && (obra.getDuracion() > 150)) {
                            JOptionPane.showMessageDialog(null, "No hay horarios disponibles.");
                            vista.getBtnAgregar().setEnabled(false);
                        }
                    }
                } catch (Exception e) {
                    /* Void */
                }
            }
            
        });
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == vista.getComboBoxObra()) {
            obraSeleccionada = vista.getComboBoxObra().getSelectedItem().toString();

            if (!("-Seleccionar Obra-".equals(obraSeleccionada))) {/*  */
            }
        }

        if (event.getSource() == vista.getBtnAgregar()) {
            Funcion nuevaFuncion = new Funcion();
            SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");
            Obra obra = daoObra.buscarObra(vista.getComboBoxObra().getSelectedItem().toString());

            try {
                nuevaFuncion.setFechaPresentacion(new Date(vista.getjDateChooser1().getDate().getTime()));
                nuevaFuncion.setHoraPresentacion(new Time(timeFormater.parse(vista.getComboBoxHora().getSelectedItem().toString()).getTime()));
                nuevaFuncion.setObraTeatral(obraSeleccionada);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            }

            daoFuncion.agregarFuncion(nuevaFuncion, obra.getId());
        }
    }
}
