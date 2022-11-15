package Controlador;

import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

import DAO.DAOFuncion;
import DAO.DAOObra;
import Modelo.Funcion;
import Modelo.Obra;
import Vista.CambiosFunciones;
import Vista.MenuAdmi;

public class CtrlModificarEliminarFunciones implements ActionListener, MouseListener {
    private CambiosFunciones vista;
    private DAOFuncion daoFuncion;
    private DAOObra daoObra;

    public CtrlModificarEliminarFunciones(CambiosFunciones vista) {
        this.vista = vista;

        this.vista.getBtnCancelar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnModificar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Regresar al menu
        if (event.getSource() == this.vista.getBtnRegresarMenu()) {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);

            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());

                this.vista.setVisible(false);
                this.vista.dispose();
            }
        }

        //Eliminar funcion
        // Falta corregir errores de logica
        if (event.getSource() == this.vista.getBtnEliminar()) {
            this.mouseClicked(null);
        }

        // Falta agregar la verificacion de la duracion de la obra nueva
        if (event.getSource() == this.vista.getBtnModificar()) {
            Funcion funcion = new Funcion();
            Funcion funcionModificada = new Funcion();

            DateFormat formatter = new SimpleDateFormat("HH:mm");
            String fajr_prayertime = this.vista.getComboBoxHorario().getSelectedItem().toString();

            funcion.setObraTeatral(this.vista.getComboBoxObrasAgendadas().getSelectedItem().toString());
            funcion.setFechaPresentacion(new java.sql.Date(this.vista.getjDateChooser1().getDate().getTime()));

            // Se convierte el String del comboBox a un sql.Time
            try {
                funcion.setHoraPresentacion(new java.sql.Time(formatter.parse(fajr_prayertime).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            funcionModificada.setObraTeatral(this.vista.getComboBoxObraNueva().getSelectedItem().toString());
            funcionModificada
                    .setFechaPresentacion(new java.sql.Date(this.vista.getjDateChooser2().getDate().getTime()));
            String horaNueva = this.vista.getComboBoxHorarioNuevo().getSelectedItem().toString();

            try {
                funcionModificada.setHoraPresentacion(new java.sql.Time(formatter.parse(horaNueva).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Se hace el llamado al DaoFuncion para comprobar si la transaccion fue correcta
            if (daoFuncion.modificarFuncion(funcion, funcionModificada)) {
                JOptionPane.showMessageDialog(null, "Modificacion correcta");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la modificacion");
            }
        }
    }

    // Esto esta bugueado, no sirve jiji
    // No esta declarado el MouseListener
    @Override
    public void mouseClicked(MouseEvent event) {

        if (event.getSource() == this.vista.getTablaObras()) {
            int fila = this.vista.getTablaObras().getSelectedRow();
            int id = Integer.parseInt(this.vista.getTablaObras().getValueAt(fila, 5).toString());

            if (daoFuncion.eliminarFuncion(id)) {
                JOptionPane.showMessageDialog(null, "Funcion eliminada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar funcion");
            }
        }
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
