package Controlador;

import java.sql.*;
import java.text.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import DAO.*;
import ExcepcionesTeatro.ExcepcionDAOFunciones;
import ExcepcionesTeatro.ExcepcionHorarioNoDisponible;
import Modelo.*;
import Vista.CrearFuncion;
import Vista.MenuAdmi;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        agregarObrasComboBox(); 
        this.vista.setVisible(true);

    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        //boton agregar
        if (event.getSource() == vista.getBtnAgregar()) {
            int indexCombobox = this.vista.getComboBoxObra().getSelectedIndex(); 
            int idNuevaFuncion = 0; 
            try {
                obtenerDatos(indexCombobox);
                validarDisponibilidadHorario();
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
            }catch (ExcepcionHorarioNoDisponible ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }catch (ExcepcionDAOFunciones ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
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

    public void obtenerDatos(int indexCombobox)throws Exception{
        SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");
            this.funcion.setFechaPresentacion(new Date(vista.getjDateChooser1().getDate().getTime()));
            this.funcion.setHoraPresentacion(new Time(timeFormater.parse(vista.getComboBoxHora().getSelectedItem().toString()).getTime()));
            this.funcion.setObra(this.obras.get(indexCombobox - 1));
            
    }

    //Verifica que no haya más de dos obras en el dia o que no interfiera una con otra
    public void validarDisponibilidadHorario() throws Exception{
        boolean disponible=false;
        DAOFuncion daoFuncion = new DAOFuncion();
        ArrayList<Funcion> funciones = daoFuncion.obtenerFuncionPorFecha(this.funcion.getFechaPresentacion());

        if (funciones.isEmpty()) { 
        disponible=true;
        }else if (funciones.size() == 1) {
            if (!funciones.get(0).getHoraPresentacion().equals(this.funcion.getHoraPresentacion())) {
                if (funciones.get(0).getHoraPresentacion().equals(new Time(18, 0, 0))
                        && funciones.get(0).getObra().getDuracion() < 150) {
                    disponible=true;
                } else if (this.funcion.getHoraPresentacion().equals(new Time(18, 0, 0)) && funcion.getObra().getDuracion() < 150){
                    disponible=true;
                }
            } else {
                throw new ExcepcionHorarioNoDisponible("El horario no se encuentra disponible, por favor seleccione otro");
            }
        }
        if(disponible==false)
        throw new ExcepcionHorarioNoDisponible("No hay horarios disponibles. Por favor seleccione otro día");
    }
    
    //Inicializa la vista con las obras existentes
    public void agregarObrasComboBox() {
        DAOObra daoObras = new DAOObra();
        this.obras = daoObras.obtenerObrasRegistradas();
        
        for (Obra obra : obras) {
            vista.getComboBoxObra().addItem(obra.getNombre());
        }
    }

    public void limpiarCampos() {
        this.vista.getComboBoxHora().setSelectedIndex(0);
        this.vista.getComboBoxObra().setSelectedIndex(0);
        this.vista.getjDateChooser1().setDate(null);
    }
}
