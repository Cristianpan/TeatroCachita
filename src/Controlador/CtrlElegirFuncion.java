package Controlador;

import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JOptionPane;

import DAO.DAOFuncion;
import Modelo.Funcion;
import Modelo.Ticket;
import Vista.ElegirAsientos;
import Vista.ElegirFuncion;

public class CtrlElegirFuncion implements ActionListener, ItemListener {
    private ElegirFuncion vista;
    private ArrayList<Funcion> funcionesDisponibles = new ArrayList<>();

    public CtrlElegirFuncion(ElegirFuncion vista) {
        this.vista = vista;
        this.vista.getBtnElegirAsientos().addActionListener(this);
        this.vista.getBtnRegresarMenu().addActionListener(this);
        this.vista.getCancelar().addActionListener(this);
        this.vista.getComboBoxObra().addItemListener(this);
        this.vista.setVisible(true);
        agregarObras();

        // Agregando action listener al calendar
        this.vista.getCalendar().addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                agregarObras();
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int indexObra = this.vista.getComboBoxObra().getSelectedIndex();

        if (indexObra != 0 && indexObra != -1) {
            agregarHorarios(indexObra);
            this.vista.getTxtPrecio()
                    .setText(String.valueOf(this.funcionesDisponibles.get(indexObra - 1).getObra().getPrecioBoleto()));
            this.vista.getTxtResumen().setText(this.funcionesDisponibles.get(indexObra - 1).getObra().getResumen());
        } else {
            this.vista.getComboBoxHorario().removeAllItems();
            this.vista.getTxtPrecio().setText(null);
            this.vista.getTxtResumen().setText(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // btn Elegir asientos
        if (event.getSource() == this.vista.getBtnElegirAsientos()) {
           if (this.vista.getComboBoxObra().getSelectedIndex() != 0 && !this.funcionesDisponibles.isEmpty()){
                if(this.vista.getComboBoxHorario().getSelectedIndex() != 0 ){
                    Ticket ticket = new Ticket(); 
                    ticket.setNombreObra(this.vista.getComboBoxObra().getSelectedItem().toString());
                    new CtrlElegirAsientos(ticket, new ElegirAsientos(), obtenerFuncionSeleccionada()); 
                    this.vista.setVisible(false);
                    this.vista.dispose();
                } else {
                    JOptionPane.showMessageDialog(this.vista, "Por favor seleccione el horario disponible.");
                }

           } else {
                JOptionPane.showMessageDialog(this.vista, "Por favor seleccione una función disponible.");
           } 
        }
    }

    public Funcion obtenerFuncionSeleccionada(){

        for (Funcion funcion: funcionesDisponibles){
            if (this.vista.getComboBoxHorario().getSelectedItem().toString().equals(String.valueOf(funcion.getHoraPresentacion())) && this.vista.getComboBoxObra().getSelectedItem().toString().equals(funcion.getObra().getNombre())){
                return funcion; 
            }
        }
        
        return null;
    }

    public void agregarHorarios(int index) {
        this.vista.getComboBoxHorario().removeAllItems();
        this.vista.getComboBoxHorario().addItem("-Seleccionar-");
        if (this.funcionesDisponibles.size() == 2 && this.vista.getComboBoxObra().getItemCount() == 2) {
            for (Funcion funcion : funcionesDisponibles) {
                this.vista.getComboBoxHorario().addItem(String.valueOf(funcion.getHoraPresentacion()));
            }
        } else {
            this.vista.getComboBoxHorario()
                    .addItem(String.valueOf(this.funcionesDisponibles.get(index - 1).getHoraPresentacion()));
        }
    }

    public void agregarObras() {
        DAOFuncion daoFuncion = new DAOFuncion();
        this.funcionesDisponibles = daoFuncion
                .obtenerFuncionPorFecha(new Date(this.vista.getCalendar().getDate().getTime()));

        this.vista.getComboBoxObra().removeAllItems();
        this.vista.getComboBoxObra().addItem("--Selecionar obra--");

        if (!funcionesDisponibles.isEmpty()) {
            String obra = null;
            for (Funcion funcion : funcionesDisponibles) {
                if (!funcion.getObra().getNombre().equals(obra)) {
                    this.vista.getComboBoxObra().addItem(funcion.getObra().getNombre());
                }
                obra = funcion.getObra().getNombre();
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "No existen funciones registradas para la fecha seleccionada.");
        }
    }

}
