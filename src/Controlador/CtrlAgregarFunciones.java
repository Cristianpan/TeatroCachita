package Controlador;

import java.sql.*;
import java.text.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import DAO.*;
import Modelo.*;
import Vista.CrearFuncion;
import Vista.MenuAdmi;

public class CtrlAgregarFunciones implements ActionListener {

    private CrearFuncion vista;
    private Funcion funcion; 
    ArrayList<Obra> obras; 

    public CtrlAgregarFunciones(CrearFuncion vista, Funcion funcion) {
        this.funcion = funcion; 
        this.vista = vista;
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnRegresarMenu().addActionListener(this);
        this.vista.getBtnCancelar().addActionListener(this);
        this.vista.setVisible(true);
        agregarObrasComboBox();
        agregarHorariosComboBox(); 

    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        //boton agregar
        if (event.getSource() == vista.getBtnAgregar()) {
            int indexCombobox = this.vista.getComboBoxObra().getSelectedIndex(); 
            int idNuevaFuncion = 0; 

           
            if (obtenerDatos(indexCombobox)){
                if (validarDisponibilidadHorario()){
                    DAOFuncion daoFuncion = new DAOFuncion();                         
                    idNuevaFuncion = daoFuncion.agregarFuncion(funcion); 

                    if (idNuevaFuncion != 0){
                        DAOSala daoSala = new DAOSala(); 
                        daoSala.crearNuevaSala(idNuevaFuncion); 
                        JOptionPane.showMessageDialog(this.vista, "Registro exitoso");
                        limpiarCampos();                            
                    } else {
                        JOptionPane.showMessageDialog(this.vista, "Ha habido un error. Por favor intente nuevamente");
                        limpiarCampos();
                    }
                }
            }
        }

        //boton regresarMenu 
        if (event.getSource() == vista.getBtnRegresarMenu()){
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.vista.setVisible(false);
                this.vista.dispose();
            }
        }
        
        // boton cancelar
        if (event.getSource() == this.vista.getBtnCancelar()){
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Desea cancelar la acción?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            if (opcion == 0) {
                limpiarCampos();
            }
        }
    }

    public boolean obtenerDatos(int indexCombobox){
        SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");
    
        try {
            this.funcion.setFechaPresentacion(new Date(vista.getjDateChooser1().getDate().getTime()));
            this.funcion.setHoraPresentacion(new Time(timeFormater.parse(vista.getComboBoxHora().getSelectedItem().toString()).getTime()));
            this.funcion.setObra(this.obras.get(indexCombobox - 1));
            return true; 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false; 
        }
        
    }

    //Verifica que no haya más de dos obras en el dia o que no interfiera una con otra
    public boolean validarDisponibilidadHorario() {
        DAOFuncion daoFuncion = new DAOFuncion();
        ArrayList<Funcion> funciones = daoFuncion.buscarPorFecha(this.funcion.getFechaPresentacion());

        if (funciones.isEmpty()) {
            return true;
        } else if (funciones.size() == 1) {
            if (!funciones.get(0).getHoraPresentacion().equals(this.funcion.getHoraPresentacion())) {
                if (funciones.get(0).getHoraPresentacion().equals(new Time(18, 0, 0))
                        && funciones.get(0).getObra().getDuracion() < 150) {
                        return true; 
                } else if (this.funcion.getHoraPresentacion().equals(new Time(18, 0, 0)) && funcion.getObra().getDuracion() < 150){
                        return true; 
                }
            } else {
                JOptionPane.showMessageDialog(vista, "El horario no se encuentra disponible, por favor seleccione otro");
                return false; 
            }
        }

        JOptionPane.showMessageDialog(this.vista, "No hay horarios disponibles. Por favor seleccione otro día");
        return false; 
    }
    
    //Inicializa la vista con las obras existentes
    public void agregarObrasComboBox() {
        DAOObra daoObras = new DAOObra();
        this.obras = daoObras.obtenerObrasRegistradas();
        vista.getComboBoxObra().addItem("-Seleccionar Obra-");
        
        for (Obra obra : obras) {
            vista.getComboBoxObra().addItem(obra.getNombre());
        }
    }
    
    //Agrega horarios disponibles
    public void agregarHorariosComboBox(){
        this.vista.getComboBoxHora().addItem("-Seleccionar horario-");
        this.vista.getComboBoxHora().addItem("18:00");
        this.vista.getComboBoxHora().addItem("20:30");
    }

    public void limpiarCampos() {
        this.vista.getComboBoxHora().setSelectedIndex(0);
        this.vista.getComboBoxObra().setSelectedIndex(0);
        this.vista.getjDateChooser1().setDate(null);
    }
}
