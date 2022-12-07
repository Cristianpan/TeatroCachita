/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.ElegirAsientos;
import Vista.ElegirFuncion;
import Vista.ImprimirTicketBoletos;
import dao.*;
import modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class CtrlElegirAsientos implements ActionListener {
    private Ticket ticket;
    private ElegirAsientos frmElegirAsiento;
    private Funcion funcionActual;
    private double precioTotal = 0;
    private ArrayList<String> asientosSeleccionados = new ArrayList<>();

    public CtrlElegirAsientos(Ticket ticket, ElegirAsientos frmElegirAsiento, Funcion funcion) {
        this.ticket = ticket;
        this.frmElegirAsiento = frmElegirAsiento;
        this.funcionActual = funcion;

        this.frmElegirAsiento.getBtnAceptar().addActionListener(this);
        this.frmElegirAsiento.getBtnCancelar().addActionListener(this);
        this.frmElegirAsiento.getBtnRegresar().addActionListener(this);

        inicializarAsientos();
        this.frmElegirAsiento.setVisible(true);
    }

    // Añade el action listener a cada asiento e inicializa a cada uno con sus
    // valores
    public void inicializarAsientos() {
        try {
            DAOSala dao = new DAOSala();
            
            this.funcionActual.setAsientos(dao.obtenerAsientos(this.funcionActual.getId()));
            int aux = 0;
    
            // Asientos A
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    this.frmElegirAsiento.getAsientosA()[i][j].addActionListener(this);
                    if (this.funcionActual.getAsientos().get(aux) == 1) {
                        this.frmElegirAsiento.getAsientosA()[i][j].setEnabled(false);
                    }
                    aux++;
                }
            }
    
            // Asientos B1
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    this.frmElegirAsiento.getAsientosB1()[i][j].addActionListener(this);
                    if (this.funcionActual.getAsientos().get(aux) == 1) {
                        this.frmElegirAsiento.getAsientosB1()[i][j].setEnabled(false);
                    }
                    aux++;
                }
            }
    
            // Asientos B2
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    this.frmElegirAsiento.getAsientosB2()[i][j].addActionListener(this);
                    if (this.funcionActual.getAsientos().get(aux) == 1) {
                        this.frmElegirAsiento.getAsientosB2()[i][j].setEnabled(false);
                    }
                    aux++;
                }
            }
    
            // Asientos C
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    this.frmElegirAsiento.getAsientosC()[i][j].addActionListener(this);
                    if (this.funcionActual.getAsientos().get(aux) == 1) {
                        this.frmElegirAsiento.getAsientosC()[i][j].setEnabled(false);
                    }
                    aux++;
                }
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frmElegirAsiento, "Ha ocurrido un error en el sistema. Por favor intente nuevamente.");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // BOTON CANCELAR
        if (e.getSource() == this.frmElegirAsiento.getBtnCancelar()) {
            deseleccionarAsientos();
        }
    
        // BOTON REGRESAR
        if (e.getSource() == this.frmElegirAsiento.getBtnRegresar()) {
            new CtrlElegirFuncion(new ElegirFuncion());
            cerrarVenta();
        }
        
        // BOTON ACEPTAR
        if (e.getSource() == this.frmElegirAsiento.getBtnAceptar()) {
            double montoApagar = 0;

            if (this.precioTotal > 0){
                try {
                    montoApagar = Double.parseDouble(this.frmElegirAsiento.getTxtMontoPagar().getText());
                    if (montoApagar >= this.precioTotal){
                        DAOSala daoSala = new DAOSala(); 
                        // actualiza la base de datos
                        daoSala.ocuparAsiento(asientosSeleccionados, this.funcionActual.getId()); 
                        // abrir siguiente ventana y la de funciones
                        crearTicket();
                        new CtrlImprimirTicketBoleto(ticket, new ImprimirTicketBoletos(), this.funcionActual);
                        cerrarVenta();
                    } else{
                        JOptionPane.showMessageDialog(frmElegirAsiento, "Monto a pagar insuficiente");
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frmElegirAsiento, "El monto a pagar es incorrecto.\n Por favor ingrese un dato numérico.");
                } catch (SQLException exception){
                    JOptionPane.showMessageDialog(frmElegirAsiento, "Ha ocurrido un error con el sistema ");
                }
            } else {
                JOptionPane.showMessageDialog(frmElegirAsiento, "No se ha seleccionado algún asiento.");
            }
        }


        // TXTPRECIOAPAGAR
        DecimalFormat formatoPrecio = new DecimalFormat("#.00");

        // ASIENTOS A
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == this.frmElegirAsiento.getAsientosA()[i][j]) {
                    if (this.frmElegirAsiento.getAsientosA()[i][j].isSelected()) {
                        this.precioTotal += this.funcionActual.getObra().getPrecioBoleto()
                                * (1 + this.funcionActual.getPrecioA());
                        this.asientosSeleccionados.add(this.frmElegirAsiento.getAsientosA()[i][j].getText().toString());
                    } else {
                        this.precioTotal -= this.funcionActual.getObra().getPrecioBoleto()
                                * (1 + this.funcionActual.getPrecioA());
                        this.asientosSeleccionados
                                .remove(this.frmElegirAsiento.getAsientosA()[i][j].getText().toString());
                    }
                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }

        // ASIENTOSC
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == this.frmElegirAsiento.getAsientosC()[i][j]) {
                    if (this.frmElegirAsiento.getAsientosC()[i][j].isSelected()) {
                        this.precioTotal += this.funcionActual.getObra().getPrecioBoleto();
                        this.asientosSeleccionados.add(this.frmElegirAsiento.getAsientosC()[i][j].getText().toString());

                    } else {
                        this.precioTotal -= this.funcionActual.getObra().getPrecioBoleto(); 
                        this.asientosSeleccionados.remove(this.frmElegirAsiento.getAsientosC()[i][j].getText().toString());
                    }
                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }

        // ASIENTOSB1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (e.getSource() == this.frmElegirAsiento.getAsientosB1()[i][j]) {
                    if (this.frmElegirAsiento.getAsientosB1()[i][j].isSelected()) {
                        this.precioTotal += this.funcionActual.getObra().getPrecioBoleto()
                                * (1 + this.funcionActual.getPrecioB());
                        this.asientosSeleccionados
                                .add(this.frmElegirAsiento.getAsientosB1()[i][j].getText().toString());

                    } else {
                        this.precioTotal -= this.funcionActual.getObra().getPrecioBoleto()
                                * (1 + this.funcionActual.getPrecioB());
                        this.asientosSeleccionados
                                .remove(this.frmElegirAsiento.getAsientosB1()[i][j].getText().toString());
                    }

                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }

        // ASIENTOSB2
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (e.getSource() == this.frmElegirAsiento.getAsientosB2()[i][j]) {
                    if (this.frmElegirAsiento.getAsientosB2()[i][j].isSelected()) {
                        this.precioTotal += this.funcionActual.getObra().getPrecioBoleto() * (1 + this.funcionActual.getPrecioB());
                        this.asientosSeleccionados.add(this.frmElegirAsiento.getAsientosB2()[i][j].getText().toString());

                    } else {
                        this.precioTotal -= this.funcionActual.getObra().getPrecioBoleto()* (1 + this.funcionActual.getPrecioB());
                        this.asientosSeleccionados.remove(this.frmElegirAsiento.getAsientosB2()[i][j].getText().toString());
                    }

                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }

    }

    public void deseleccionarAsientos() {
        // asientosA
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.frmElegirAsiento.getAsientosA()[i][j].setSelected(false);
            }
        }

        // asientosC
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.frmElegirAsiento.getAsientosC()[i][j].setSelected(false);
            }
        }

        // asientosB1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.frmElegirAsiento.getAsientosB1()[i][j].setSelected(false);
            }
        }

        // asientosB2
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.frmElegirAsiento.getAsientosB2()[i][j].setSelected(false);
            }
        }

        this.precioTotal = 0;
        this.frmElegirAsiento.getTxtPrecioPagar().setText(String.valueOf(this.precioTotal));
    }

    public void crearTicket() {
        Calendar date = Calendar.getInstance(); 

        Date fechaVenta = new Date(date.getTimeInMillis());
        Time horaVenta = new Time(date.getTimeInMillis()); 
        this.ticket.setHoraVenta(horaVenta);
        this.ticket.setFechaVenta(fechaVenta);
        this.ticket.setBoletosVendidos(asientosSeleccionados);
        this.ticket.setTotalVenta(this.precioTotal);
        this.ticket.setMontoEntregado(Double.parseDouble(this.frmElegirAsiento.getTxtMontoPagar().getText()));
        this.ticket.obtenerCambio();
    }

    public void cerrarVenta() {
        this.frmElegirAsiento.setVisible(false);
        this.frmElegirAsiento.dispose();
    }
}
