package Controlador;

import java.sql.*;
import java.text.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import DAO.*;
import Modelo.*;
import Vista.*;

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

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == this.vista.getBtnRegresarMenu()) {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);

            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());

                this.vista.setVisible(false);
                this.vista.dispose();
            }
        }

        if (event.getSource() == vista.getComboBoxObra()) {
            obraSeleccionada = vista.getComboBoxObra().getSelectedItem().toString();

            if (!("-Seleccionar Obra-".equals(obraSeleccionada))) {/*  */}
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
            JOptionPane.showMessageDialog(null, "Función agregada correctamente.");
            this.vista.limpiarCampos();
        }
    }
}
