package Controlador;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import DAO.DAOTicket;
import Modelo.Ticket;
import Vista.*;


public class CtrlReportes implements ActionListener {
    private Reportes vista;

    public CtrlReportes(Reportes vista) {
        this.vista = vista;
        this.vista.setVisible(true);
        this.vista.getBtnBuscar().addActionListener(this);
        this.vista.getBtnRegresar().addActionListener(this);
        this.vista.getComboBoxMes().addActionListener(this);
        llenarTabla(""); 
        this.vista.setVisible(true);

    }    


    @Override
    public void actionPerformed(ActionEvent event){

        //boton regresar
        if (event.getSource() == this.vista.getBtnRegresar()) {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);

            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.vista.setVisible(false);
                this.vista.dispose();
            }
        }
        //boton buscar
        if (event.getSource() == this.vista.getBtnBuscar()){
            int mes = this.vista.getComboBoxMes().getSelectedIndex(); 
            int dia = this.vista.getComboBoxDia().getSelectedIndex(); 
            String date; 

            if (mes != 0){
                date = "-" + mes;                 
                if (dia != 0){
                    date = date + "-" + dia; 
                }
                llenarTabla(date);
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione un mes");
            }
            
        }

        //combo box de los meses
        if (event.getSource() == this.vista.getComboBoxMes()){
            if(this.vista.getComboBoxMes().getSelectedIndex() != 0){
                agregarDias(this.vista.getComboBoxMes().getSelectedIndex()); 
            }
        }
    }

    //Agrega la cantidad de días al combo box de dias, dependiendo del mes seleccionado
    public void agregarDias(int numMes){
        
        this.vista.getComboBoxDia().removeAllItems();

        this.vista.getComboBoxDia().addItem("-Seleccione un día-");

        switch(numMes){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: {
                for (int i = 1; i <= 31; i++){
                    this.vista.getComboBoxDia().addItem(String.valueOf(i));
                }
            } break; 
            case 2: {
                for (int i = 1; i <= 28; i++){
                    this.vista.getComboBoxDia().addItem(String.valueOf(i));
                }
            } break; 
            default: {
                for (int i = 1; i <= 30; i++){
                    this.vista.getComboBoxDia().addItem(String.valueOf(i));
                }
            }
        }

    }

    public void llenarTabla(String date){
        Calendar dateToday = Calendar.getInstance(); 
        int year = dateToday.get(Calendar.YEAR); 
        String auxDate = year + date; 
        
        DAOTicket daoTicket = new DAOTicket(); 
        ArrayList<Ticket> tickets = daoTicket.obtenerTickets(auxDate); 
        
        DefaultTableModel tabla = new DefaultTableModel();
        String[] fila = new String[5];

        Double ventaPromedio = 0.0; 

        tabla.addColumn("Num. Venta");
        tabla.addColumn("Fecha");
        tabla.addColumn("Obra");
        tabla.addColumn("Boletos");
        tabla.addColumn("T. Vendido");


        for (Ticket ticket : tickets) {
            fila[0] = String.valueOf(ticket.getNumVenta());
            fila[1] = String.valueOf(ticket.getFechaVenta());
            fila[2] = ticket.getNombreObra(); 
            fila[3] = ticket.getBoletosVendidos().toString();  
            fila[4] = String.valueOf(ticket.getTotalVenta()); 

            ventaPromedio = ventaPromedio + ticket.getTotalVenta() / tickets.size(); 
            
            tabla.addRow(fila);
        }

        this.vista.getTxtVentaPromedio().setText("$" + String.valueOf(ventaPromedio));
        this.vista.getTableReporte().setModel(tabla);
    }

}
