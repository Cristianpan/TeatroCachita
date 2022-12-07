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
import Modelo.User;
import Vista.ElegirAsientos;
import Vista.ElegirFuncion;
import Vista.Login;
import Vista.MenuAdmi;

public class CtrlElegirFuncion implements ActionListener, ItemListener {
    private ElegirFuncion frmElegirFuncion;
    private ArrayList<Funcion> funcionesDisponibles = new ArrayList<>();
    private static String tipoUsuario;

    public CtrlElegirFuncion(ElegirFuncion frmElegirFuncion) {
        this.frmElegirFuncion = frmElegirFuncion;
        this.frmElegirFuncion.getBtnElegirAsientos().addActionListener(this);
        this.frmElegirFuncion.getBtnRegresar().addActionListener(this);
        this.frmElegirFuncion.getBtnCancelar().addActionListener(this);
        this.frmElegirFuncion.getComboBoxObra().addItemListener(this);
        this.frmElegirFuncion.setVisible(true);
        agregarObras();
        inicializarVista();

        // Agregando action listener al calendar
        this.frmElegirFuncion.getCalendar().addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                agregarObras();
            }
        });
    }
    
    public CtrlElegirFuncion(ElegirFuncion frmElegirFuncion, String tipoUsuario){
        this(frmElegirFuncion);
        this.tipoUsuario= tipoUsuario;
        inicializarVista();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int indexObra = this.frmElegirFuncion.getComboBoxObra().getSelectedIndex();

        if (indexObra != 0 && indexObra != -1) {
            agregarHorarios(indexObra);
            this.frmElegirFuncion.getTxtPrecio()
                    .setText(String.valueOf(this.funcionesDisponibles.get(indexObra - 1).getObra().getPrecioBoleto()));
            this.frmElegirFuncion.getTxtResumen().setText(this.funcionesDisponibles.get(indexObra - 1).getObra().getResumen());
        } else {
            this.frmElegirFuncion.getComboBoxHorario().removeAllItems();
            this.frmElegirFuncion.getTxtPrecio().setText(null);
            this.frmElegirFuncion.getTxtResumen().setText(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // btn Elegir asientos
        if (event.getSource() == this.frmElegirFuncion.getBtnElegirAsientos()) {
           if (this.frmElegirFuncion.getComboBoxObra().getSelectedIndex() != 0 && !this.funcionesDisponibles.isEmpty()){
                if(this.frmElegirFuncion.getComboBoxHorario().getSelectedIndex() != 0 ){
                    Ticket ticket = new Ticket(); 
                    ticket.setNombreObra(this.frmElegirFuncion.getComboBoxObra().getSelectedItem().toString());
                    new CtrlElegirAsientos(ticket, new ElegirAsientos(), obtenerFuncionSeleccionada()); 
                    this.frmElegirFuncion.setVisible(false);
                    this.frmElegirFuncion.dispose();
                } else {
                    JOptionPane.showMessageDialog(this.frmElegirFuncion, "Por favor seleccione el horario disponible.");
                }

           } else {
                JOptionPane.showMessageDialog(this.frmElegirFuncion, "Por favor seleccione una función disponible.");
           } 
        }
        //btn cancelar
        if(event.getSource() == this.frmElegirFuncion.getBtnCancelar()){
            this.frmElegirFuncion.getComboBoxObra().setSelectedIndex(0);
        }
        
        //btn regresar
        if (event.getSource() == this.frmElegirFuncion.getBtnRegresar()) {
            if(tipoUsuario=="administrador"){
            new CtrlMenu(new MenuAdmi());
            cerrarVentana();
            }else{
                int opcion = JOptionPane.showConfirmDialog(frmElegirFuncion, "¿Desea cerrar sesión?", null,
                        JOptionPane.YES_NO_OPTION, 1);

                if (opcion == 0){
                    new CtrlLogin(new User(), new Login()); 
                    this.frmElegirFuncion.setVisible(false);
                    this.frmElegirFuncion.dispose();;
                }
            }
        }
    }

    public void cerrarVentana(){
        this.frmElegirFuncion.setVisible(false);
        this.frmElegirFuncion.dispose();
    }
    public Funcion obtenerFuncionSeleccionada(){

        for (Funcion funcion: funcionesDisponibles){
            if (this.frmElegirFuncion.getComboBoxHorario().getSelectedItem().toString().equals(String.valueOf(funcion.getHoraPresentacion())) && this.frmElegirFuncion.getComboBoxObra().getSelectedItem().toString().equals(funcion.getObra().getNombre())){
                return funcion; 
            }
        }
        
        return null;
    }

    public void agregarHorarios(int index) {
        this.frmElegirFuncion.getComboBoxHorario().removeAllItems();
        this.frmElegirFuncion.getComboBoxHorario().addItem("-Seleccionar-");
        if (this.funcionesDisponibles.size() == 2 && this.frmElegirFuncion.getComboBoxObra().getItemCount() == 2) {
            for (Funcion funcion : funcionesDisponibles) {
                this.frmElegirFuncion.getComboBoxHorario().addItem(String.valueOf(funcion.getHoraPresentacion()));
            }
        } else {
            this.frmElegirFuncion.getComboBoxHorario()
                    .addItem(String.valueOf(this.funcionesDisponibles.get(index - 1).getHoraPresentacion()));
        }
    }

    public void agregarObras() {
        DAOFuncion daoFuncion = new DAOFuncion();
        this.funcionesDisponibles = daoFuncion
                .obtenerFuncionPorFecha(new Date(this.frmElegirFuncion.getCalendar().getDate().getTime()));

        this.frmElegirFuncion.getComboBoxObra().removeAllItems();
        this.frmElegirFuncion.getComboBoxObra().addItem("--Selecionar obra--");

        if (!funcionesDisponibles.isEmpty()) {
            String obra = null;
            for (Funcion funcion : funcionesDisponibles) {
                if (!funcion.getObra().getNombre().equals(obra)) {
                    this.frmElegirFuncion.getComboBoxObra().addItem(funcion.getObra().getNombre());
                }
                obra = funcion.getObra().getNombre();
            }
        } else {
            JOptionPane.showMessageDialog(this.frmElegirFuncion, "No existen funciones registradas para la fecha seleccionada.");
        }
    }
    
    public void inicializarVista(){
        if(tipoUsuario == "administrador"){
            this.frmElegirFuncion.getLabelRegresar().setText("Menú");
        }else{
            this.frmElegirFuncion.getLabelRegresar().setText("Salir");
        }
    }

}
