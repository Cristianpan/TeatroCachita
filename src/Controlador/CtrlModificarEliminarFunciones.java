package Controlador;

import java.sql.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane; 
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import DAO.*; 
import Modelo.*; 
import Vista.*;


public class CtrlModificarEliminarFunciones implements ActionListener, MouseListener {
    private CambiosFunciones vista;
    private ArrayList<Funcion> funciones;
    private ArrayList<Obra> obras;
    private Funcion funcion;

    public CtrlModificarEliminarFunciones(CambiosFunciones vista, Funcion funcion) {
        this.vista = vista;
        this.funcion = funcion;
        this.vista.getBtnCancelar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnModificar().addActionListener(this);
        this.vista.getBtnRegresarMenu().addActionListener(this);
        this.vista.getTabla().addMouseListener(this);
        llenarTabla();
        agregarHorariosComboBox();
        agregarObrasComboBox();

        this.vista.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // boton regresar al menu
        if (event.getSource() == this.vista.getBtnRegresarMenu()) {

            int opcion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);

            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.vista.setVisible(false);
                this.vista.dispose();
            }
        }

        // boton cancelar
        if (event.getSource() == this.vista.getBtnCancelar()) {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Desea cancelar la acción?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            if (opcion == 0) {
                limpiarCampos();
            }
        }

        // boton eliminar
        if (event.getSource() == this.vista.getBtnEliminar()) {
            int fila = this.vista.getTabla().getSelectedRow();

            int opcion = JOptionPane.showConfirmDialog(vista,
                    "¿Desea eliminar la funcion seleccionada?", null,
                    JOptionPane.YES_NO_OPTION, 2);

            if (opcion == 0) {
                DAOFuncion daoFuncion = new DAOFuncion();

                if (daoFuncion.eliminarFuncion(funciones.get(fila).getId())) {
                    ((DefaultTableModel) this.vista.getTabla().getModel()).removeRow(fila);
                    funciones.remove(fila);
                    JOptionPane.showMessageDialog(this.vista, "La función ha sido eliminada");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this.vista, "Ha habido un error. Por favor intente nuevamente");
                }

            }
        }

        // boton modificar
        if (event.getSource() == this.vista.getBtnModificar()) {
            int fila = this.vista.getTabla().getSelectedRow();
            DAOFuncion daoFuncion = new DAOFuncion();

            int opcion = JOptionPane.showConfirmDialog(vista,
                    "¿Desea modificar la funcion seleccionada?", null,
                    JOptionPane.YES_NO_OPTION, 2);

            if (opcion == 0) {

                if (obtenerDatos(fila)) {
                    if (validarDisponibilidadHorario()){
                        if (daoFuncion.modificarFuncion(this.funcion)) {
                            // actualiza la fila de la función en la tabla
                            JOptionPane.showMessageDialog(this.vista, "Funcion modificada");
                            this.vista.getTabla().setValueAt(this.funcion.getObra().getNombre(), fila, 1);
                            this.vista.getTabla().setValueAt(this.funcion.getFechaPresentacion(), fila, 2);
                            this.vista.getTabla().setValueAt(this.funcion.getHoraPresentacion(), fila, 3);
                            // actualiza el arraylist
                            this.funciones.set(fila, funcion);

                            //limpiar los campos 
                            limpiarCampos();
                        } else {
                            JOptionPane.showInternalMessageDialog(null, "Ha habido un error. Por favor intente nuevamente");
                        }
                    }
                }
            }
        }

    }

    // Obtiene los datos de la fila seleccionada en la tabla y los muestra en los
    // campos
    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == this.vista.getTabla()) {
            int fila = this.vista.getTabla().getSelectedRow();
            this.vista.getComboBoxObraNueva().setSelectedItem(this.funciones.get(fila).getObra().getNombre());
            this.vista.getjDateChooser1().setDate(this.funciones.get(fila).getFechaPresentacion());
            this.vista.getComboBoxHorarioNuevo()
                    .setSelectedItem(String.valueOf(this.funciones.get(fila).getHoraPresentacion()).substring(0, 5));
        }
    }

    /*
     * Obtiene todos los datos en en los campos Obra, fecha, horario
     * Retorna falso si algún campo es vacio
     */
    public boolean obtenerDatos(int fila) {
        SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");

        try {
            this.funcion.setId(this.funciones.get(fila).getId());
            this.funcion.setFechaPresentacion(new Date(vista.getjDateChooser1().getDate().getTime()));
            this.funcion.setHoraPresentacion(new Time(
                    timeFormater.parse(vista.getComboBoxHorarioNuevo().getSelectedItem().toString()).getTime()));
            this.funcion.setObra(this.obras.get(this.vista.getComboBoxObraNueva().getSelectedIndex() - 1));
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }
    }

    // Limpia todos los campos después modificar o eliminar
    public void limpiarCampos() {
        this.vista.getComboBoxHorarioNuevo().setSelectedIndex(0);
        this.vista.getComboBoxObraNueva().setSelectedIndex(0);
        this.vista.getjDateChooser1().setDate(null);
    }

    // Inicializa el combobox de obras con las obras existentes
    public void agregarObrasComboBox() {
        DAOObra daoObras = new DAOObra();
        this.obras = daoObras.obtenerObrasRegistradas();
        this.vista.getComboBoxObraNueva().addItem("-Seleccionar Obra-");
        for (Obra obra : this.obras) {
            this.vista.getComboBoxObraNueva().addItem(obra.getNombre());
        }
    }

    // Agrega los horarios disponibles
    public void agregarHorariosComboBox() {
        this.vista.getComboBoxHorarioNuevo().addItem("-Seleccionar horario-");
        this.vista.getComboBoxHorarioNuevo().addItem("18:00");
        this.vista.getComboBoxHorarioNuevo().addItem("20:30");
    }

    // Inicializa la tabal con las funciones que han sido registradas
    public void llenarTabla() {
        DefaultTableModel tabla = new DefaultTableModel();
        DAOFuncion daoFuncion = new DAOFuncion();
        String[] fila = new String[4];

        tabla.addColumn("id");
        tabla.addColumn("Obra");
        tabla.addColumn("Fecha");
        tabla.addColumn("Horario");

        funciones = daoFuncion.obtenerFuncionesRegistradas();

        for (Funcion funcion : funciones) {
            fila[0] = String.valueOf(funcion.getId());
            fila[1] = funcion.getObra().getNombre();
            fila[2] = String.valueOf(funcion.getFechaPresentacion());
            fila[3] = String.valueOf(funcion.getHoraPresentacion());
            tabla.addRow(fila);
        }
        this.vista.getTabla().setModel(tabla);
    }

    /*
     * Valida si existe el horario disponible para el registro de la función
     * Retorna true si el horario de la función seleccionada se encuentra disponible
     * y si no interfiere con alguna otra función
     */
    public boolean validarDisponibilidadHorario() {
        DAOFuncion daoFuncion = new DAOFuncion();
        ArrayList<Funcion> funciones = daoFuncion.buscarPorFecha(this.funcion.getFechaPresentacion());
        
        if (funciones.isEmpty()) {
            
            return true;

        } else if (funciones.size() == 1) {
            
            if (!funciones.get(0).getHoraPresentacion().equals(this.funcion.getHoraPresentacion())) {
                if (funciones.get(0).getHoraPresentacion().equals(new Time(18, 0, 0)) && funciones.get(0).getObra().getDuracion() < 150) {
                    
                    return true;

                } else if (this.funcion.getHoraPresentacion().equals(new Time(18, 0, 0)) && funcion.getObra().getDuracion() < 150) {
                   
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(vista,"El horario no se encuentra disponible. Por favor seleccione otro");
                return false;
            }
        } else {
            for(Funcion funcion: funciones){
                if (funcion.getId() == this.funcion.getId() && funcion.getHoraPresentacion().equals(this.funcion.getHoraPresentacion())){
                    return true; 
                }
            }
        }

        JOptionPane.showMessageDialog(this.vista, "No hay horarios disponibles. Por favor seleccione otro día");
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
