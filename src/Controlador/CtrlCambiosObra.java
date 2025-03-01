/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.CambiosObra;
import Vista.MenuAdmi;
import dao.DAOFuncion;
import dao.DAOObra;
import excepciones.ExcepcionCamposVacios;
import excepciones.ExcepcionDAOFunciones;
import modelo.Obra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CtrlCambiosObra implements ActionListener {
    private Obra obra;
    private CambiosObra frmCambiosObra;

    public CtrlCambiosObra(Obra obra, CambiosObra frmCambiosObra) {
        this.obra = obra;
        this.frmCambiosObra = frmCambiosObra;
        agregarObrasComboBox();

        this.frmCambiosObra.getBtnCancelar().addActionListener(this);
        this.frmCambiosObra.getBtnEliminar().addActionListener(this);
        this.frmCambiosObra.getBtnModificar().addActionListener(this);
        this.frmCambiosObra.getBtnRegresarMenu().addActionListener(this);
        this.frmCambiosObra.getBtnBuscar().addActionListener(this);

        this.frmCambiosObra.setVisible(true);
    }

    public void agregarObrasComboBox() {
        DAOObra daoObras = new DAOObra();
        try {
            ArrayList<Obra> obras = daoObras.obtenerObrasRegistradas();
            this.frmCambiosObra.getComboBoxObra().addItem("-Seleccionar Obra-");

            for(Obra obra : obras){
                this.frmCambiosObra.getComboBoxObra().addItem(obra.getNombre());

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frmCambiosObra, "Ha ocurrido un error, por favor intente nuevamente.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // agregar todos los datos
        if (e.getSource() == this.frmCambiosObra.getBtnBuscar()) {
            String obraSeleccionada = this.frmCambiosObra.getComboBoxObra().getSelectedItem().toString();
            if (obraSeleccionada != "-Seleccionar Obra-") {
                try {
                    DAOObra daoObra = new DAOObra();
                    Obra obra = daoObra.buscarObra(obraSeleccionada);
                    rellenarCampos(obra);
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(frmCambiosObra,
                            "Ha ocurrido un error en el sistema.\nPor favor intente nuevamente.");
                }
            } else {
                JOptionPane.showMessageDialog(frmCambiosObra, "No se ha seleccionado alguna obra");
            }
        }

        // boton cancelar
        if (e.getSource() == this.frmCambiosObra.getBtnCancelar()) {
            int opcion = JOptionPane.showConfirmDialog(frmCambiosObra, "¿Desea cancelar la acción?", null,
                    JOptionPane.YES_NO_OPTION, -1);
            if (opcion == 0) {
                limpiarCampos();
            }
        }

        // regresar al menu
        if (e.getSource() == this.frmCambiosObra.getBtnRegresarMenu()) {
            int opcion = JOptionPane.showConfirmDialog(frmCambiosObra, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.frmCambiosObra.setVisible(false);
                this.frmCambiosObra.dispose();
            }
        }

        // modificar
        if (e.getSource() == this.frmCambiosObra.getBtnModificar()) {
            try {
                esVacioInput();
                String obraSeleccionada = this.frmCambiosObra.getComboBoxObra().getSelectedItem().toString();
                if (obraSeleccionada != "-Seleccionar Obra-") {
                    DAOObra daoObra = new DAOObra();
                    Obra obra = daoObra.buscarObra(obraSeleccionada);

                    DAOFuncion daoFuncion = new DAOFuncion();

                    daoFuncion.obtenerFuncionesVigentes(obra.getId());

                    obtenerDatosRegistro(); // la this,obra va a tener los datos de registro

                    this.obra.setId(obra.getId());

                    if (this.obra.getNombre().equals(obra.getNombre())
                            || daoObra.buscarObra(this.obra.getNombre()) == null) {
                        daoObra.actualizarObra(this.obra);
                        JOptionPane.showMessageDialog(frmCambiosObra, "Actualización exitosa");
                        this.frmCambiosObra.getComboBoxObra().setSelectedItem(this.obra.getNombre());
                        this.frmCambiosObra.getComboBoxObra().removeAllItems();
                        agregarObrasComboBox();
                        limpiarCampos();

                    } else {
                        JOptionPane.showMessageDialog(frmCambiosObra, "Obra existente.Por favor ingrese otro nombre.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frmCambiosObra, "No se ha seleccionado alguna obra");
                }
            } catch (ExcepcionCamposVacios ex) {
                JOptionPane.showMessageDialog(frmCambiosObra, ex.getMessage());
            } catch (ExcepcionDAOFunciones ex) {
                JOptionPane.showMessageDialog(frmCambiosObra, ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmCambiosObra,
                        "El dato precio o duración es incorrecto.\nPor favor digite un numero valido");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frmCambiosObra,
                        "Ha ocurrido un error en el sistema, por favor intente nuevamente.");
            }

        }

        // eliminar
        if (e.getSource() == this.frmCambiosObra.getBtnEliminar()) {
            String obraSeleccionada = this.frmCambiosObra.getComboBoxObra().getSelectedItem().toString();

            if (obraSeleccionada != "-Seleccionar Obra-") {
                try {
                    DAOObra daoObra = new DAOObra();
                    this.obra = daoObra.buscarObra(obraSeleccionada);

                    String msg = "¿Desea eliminar la obra " + obra.getNombre() + "?";

                    int opcion = JOptionPane.showConfirmDialog(frmCambiosObra, msg, null,
                            JOptionPane.YES_NO_OPTION, 2);
                    if (opcion == 0) {
                        DAOFuncion daoFuncion = new DAOFuncion();
                        daoFuncion.obtenerFuncionesVigentes(this.obra.getId());
                        daoObra.eliminarObra(this.obra.getId());
                        JOptionPane.showMessageDialog(frmCambiosObra, "Obra Eliminada.");
                        this.frmCambiosObra.getComboBoxObra()
                                .removeItem(this.frmCambiosObra.getComboBoxObra().getSelectedItem());
                        limpiarCampos();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frmCambiosObra,
                            "Ha ocurrido un error en el sistema.\nPor favor intente nuevamente.");
                } catch (ExcepcionDAOFunciones ex) {
                    JOptionPane.showMessageDialog(frmCambiosObra, ex.getMessage());
                }

            }
        }
    }

    public void esVacioInput() throws ExcepcionCamposVacios {
        if (frmCambiosObra.getTxtNombre().getText().isEmpty() ||
                frmCambiosObra.getTxtDuracion().getText().isEmpty() ||
                frmCambiosObra.getTxtGenero().getText().isEmpty() ||
                frmCambiosObra.getTxtPrecio().getText().isEmpty() ||
                frmCambiosObra.getTxtPrimerActor().getText().isEmpty() ||
                frmCambiosObra.getTxtSegundoActor().getText().isEmpty()) {
            throw new ExcepcionCamposVacios("Todos los campos son obligatorios.");
        }

    }

    public void limpiarCampos() {
        frmCambiosObra.getTxtNombre().setText(null);
        frmCambiosObra.getTxtDuracion().setText(null);
        frmCambiosObra.getTxtGenero().setText(null);
        frmCambiosObra.getTxtPrecio().setText(null);
        frmCambiosObra.getTxtPrimerActor().setText(null);
        frmCambiosObra.getTxtSegundoActor().setText(null);
        frmCambiosObra.getTxtResumenTematico().setText(null);
        frmCambiosObra.getComboBoxObra().setSelectedItem("-Seleccionar Obra-");
    }

    public void rellenarCampos(Obra obra) {
        frmCambiosObra.getTxtNombre().setText(obra.getNombre());
        frmCambiosObra.getTxtDuracion().setText(Integer.toString(obra.getDuracion()));
        frmCambiosObra.getTxtGenero().setText(obra.getGenero());
        frmCambiosObra.getTxtPrecio().setText(Double.toString(obra.getPrecioBoleto()));
        frmCambiosObra.getTxtPrimerActor().setText(obra.getPrimerActor());
        frmCambiosObra.getTxtSegundoActor().setText(obra.getSegundoActor());
        frmCambiosObra.getTxtResumenTematico().setText(obra.getResumen());
    }

    public void obtenerDatosRegistro() throws NumberFormatException {
        obra.setNombre(frmCambiosObra.getTxtNombre().getText().trim());
        obra.setGenero(frmCambiosObra.getTxtGenero().getText().trim());
        obra.setPrimerActor(frmCambiosObra.getTxtPrimerActor().getText().trim());
        obra.setSegundoActor(frmCambiosObra.getTxtSegundoActor().getText().trim());
        obra.setResumen(frmCambiosObra.getTxtResumenTematico().getText().trim());
        obra.setDuracion(Integer.parseInt(frmCambiosObra.getTxtDuracion().getText().trim()));
        obra.setPrecioBoleto(Double.parseDouble(frmCambiosObra.getTxtPrecio().getText().trim()));
    }

}
