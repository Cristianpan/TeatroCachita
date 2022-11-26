/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.*;
import Modelo.*;
import Vista.ElegirAsientos;
import Vista.ImprimirTicketBoletos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author diana
 */
public class CtrlElegirAsientos implements ActionListener{
    private Ticket ticket;
    private ElegirAsientos frmElegirAsiento;
    private Funcion funcionActual;
    private double precioTotal=0;
    private ArrayList<String> NombreAsientos= new ArrayList<>();
    
    

    public CtrlElegirAsientos(Ticket ticket, ElegirAsientos frmElegirAsiento, Funcion funcion) {
        this.ticket = ticket;
        this.frmElegirAsiento = frmElegirAsiento;
        this.funcionActual= funcion;
        
        this.frmElegirAsiento.getBtnAceptar().addActionListener(this);
        this.frmElegirAsiento.getBtnCancelar().addActionListener(this);
        this.frmElegirAsiento.getBtnRegresar().addActionListener(this);
        accionAsientos();
        inicializarAsientos();
        this.frmElegirAsiento.setVisible(true);
    }
    
    public void accionAsientos(){
        //añade accion a asientosA
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.frmElegirAsiento.getAsientosA()[i][j].addActionListener(this);
            }
        }
        
        //añade accion a asientosC
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.frmElegirAsiento.getAsientosC()[i][j].addActionListener(this);
            }
        }
        
        //añade accion a asientosB1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.frmElegirAsiento.getAsientosB1()[i][j].addActionListener(this);
            }
        }
        
        //añade accion a asientosB2
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.frmElegirAsiento.getAsientosB2()[i][j].addActionListener(this);
            }
        }
        
    }
    
    public void inicializarAsientos(){
        DAOSala dao= new DAOSala();
        ArrayList<Integer> asientos= dao.obtenerAsientos(this.funcionActual.getId());
        int aux=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(asientos.get(aux)== 1){
                    this.frmElegirAsiento.getAsientosA()[i][j].setEnabled(false);
                }
                aux++;
            }
        }
        
        //ASIENTOSB1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(asientos.get(aux)== 1){
                    this.frmElegirAsiento.getAsientosB1()[i][j].setEnabled(false);
                }
                aux++;
            }
        }
        
        //ASIENTOSB2
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(asientos.get(aux)== 1){
                    this.frmElegirAsiento.getAsientosB2()[i][j].setEnabled(false);
                }
                aux++;
            }
        }
        
        //ASIENTOSC
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(asientos.get(aux)== 1){
                    this.frmElegirAsiento.getAsientosC()[i][j].setEnabled(false);
                }
                aux++;
            }
        }
        
    }
       
    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTON ACEPTAR
        if(e.getSource() == this.frmElegirAsiento.getBtnAceptar()){
            double precioApagar=0;
            double montoApagar=0;
            try {
                precioApagar=Double.parseDouble(this.frmElegirAsiento.getTxtPrecioPagar().getText());
                montoApagar=Double.parseDouble(this.frmElegirAsiento.getTxtMontoPagar().getText());
                
                if(montoApagar>=precioApagar){
                if(existenAsientosSeleccionados()==true){
                    DAOSala daoSala= new DAOSala();
                    DAOFuncion daoF= new DAOFuncion();
                    
                            
                        //checa que asientos del Arreglo A estan seleccionados
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 5; j++) {
                                if(this.frmElegirAsiento.getAsientosA()[i][j].isSelected()){
                                    NombreAsientos.add(this.frmElegirAsiento.getAsientosA()[i][j].getText().toString());
                                }
                            }
                        }
                        //checa que asientos del Arreglo C estan seleccionados
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 5; j++) {
                                if(this.frmElegirAsiento.getAsientosC()[i][j].isSelected()){
                                    NombreAsientos.add(this.frmElegirAsiento.getAsientosC()[i][j].getText().toString());
                                }
                            }
                        }
                        //checa que asientos del Arreglo B1 estan seleccionados
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 2; j++) {
                                if(this.frmElegirAsiento.getAsientosB1()[i][j].isSelected()){
                                    NombreAsientos.add(this.frmElegirAsiento.getAsientosB1()[i][j].getText().toString());
                                }
                            }
                        }
                        //checa que asientos del Arreglo B2 estan seleccionados
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 2; j++) {
                                if(this.frmElegirAsiento.getAsientosB2()[i][j].isSelected()){
                                    NombreAsientos.add(this.frmElegirAsiento.getAsientosB2()[i][j].getText().toString());
                                }
                            }
                        }

                        //actualiza la base de datos
                        try {
                            if(daoSala.ocuparAsiento(NombreAsientos, this.funcionActual.getId())== true){
                            //abrir siguiente ventana y la de funciones
                            JOptionPane.showMessageDialog(frmElegirAsiento,"Proceso realizado con éxito");
                            crearTicket();
                            desabilitarAsientosBD();
                                ImprimirTicketBoletos impB= new ImprimirTicketBoletos();
                                CtrlImprimirTicketBoleto ctrlI= new CtrlImprimirTicketBoleto(ticket, impB, this.funcionActual);                                
                                this.frmElegirAsiento.setVisible(false);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frmElegirAsiento,"Error" + ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(frmElegirAsiento,"No se ha seleccionado asientos");
                    }
                }else{
                    JOptionPane.showMessageDialog(frmElegirAsiento,"Monto insuficiente");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmElegirAsiento,"Por favor ingrese bien los datos");
            }
            
            
        }
        
        //BOTON CANCELAR
        if(e.getSource() == this.frmElegirAsiento.getBtnCancelar()){
            deseleccionarAsientos();
        }
        
        //BOTON REGRESAR
        if(e.getSource() == this.frmElegirAsiento.getBtnRegresar()){
            //REGRESAR ALL ELEGIR FUNCION
        }
        
       
        //TXTPRECIOAPAGAR
            DecimalFormat formatoPrecio= new DecimalFormat("#.00");
            Funcion f= new Funcion();
            DAOObra daoObra= new DAOObra();
             Obra obra= new Obra();
        try {
             obra= daoObra.buscarObra(this.ticket.getNombreObra());
             
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        //ASIENTOS A
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(e.getSource() == this.frmElegirAsiento.getAsientosA()[i][j]){
                    if(this.frmElegirAsiento.getAsientosA()[i][j].isSelected()){
                        this.precioTotal= this.precioTotal+obra.getPrecioBoleto()+obra.getPrecioBoleto()*this.funcionActual.getPrecioA();
                    }else{
                        this.precioTotal= this.precioTotal-obra.getPrecioBoleto()-obra.getPrecioBoleto()*this.funcionActual.getPrecioA();
                    }
                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }
        
        //ASIENTOSC
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(e.getSource() == this.frmElegirAsiento.getAsientosC()[i][j]){
                    if(this.frmElegirAsiento.getAsientosC()[i][j].isSelected()){
                        this.precioTotal= this.precioTotal+obra.getPrecioBoleto()+obra.getPrecioBoleto();
                    }else{
                        this.precioTotal= this.precioTotal-obra.getPrecioBoleto()-obra.getPrecioBoleto();
                    }
                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }
        
        //ASIENTOSB1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(e.getSource() == this.frmElegirAsiento.getAsientosB1()[i][j]){
                    if(this.frmElegirAsiento.getAsientosB1()[i][j].isSelected()){
                        this.precioTotal= this.precioTotal+obra.getPrecioBoleto()+obra.getPrecioBoleto()*this.funcionActual.getPrecioB();
                    }else{
                        this.precioTotal= this.precioTotal-obra.getPrecioBoleto()-obra.getPrecioBoleto()*this.funcionActual.getPrecioB();
                    }
                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }
        
        //ASIENTOSB2
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(e.getSource() == this.frmElegirAsiento.getAsientosB2()[i][j]){
                    if(this.frmElegirAsiento.getAsientosB2()[i][j].isSelected()){
                        this.precioTotal= this.precioTotal+obra.getPrecioBoleto()+obra.getPrecioBoleto()*this.funcionActual.getPrecioB();
                    }else{
                        this.precioTotal= this.precioTotal-obra.getPrecioBoleto()-obra.getPrecioBoleto()*this.funcionActual.getPrecioB();
                    }
                    this.frmElegirAsiento.getTxtPrecioPagar().setText(formatoPrecio.format(this.precioTotal));
                }
            }
        }
        
    }
    
    
    public boolean existenAsientosSeleccionados(){
        //checa que asientos del Arreglo A estan seleccionados
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(this.frmElegirAsiento.getAsientosA()[i][j].isSelected()){
                    return true;
                }
            }
        }
        //checa que asientos del Arreglo C estan seleccionados
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if(this.frmElegirAsiento.getAsientosC()[i][j].isSelected()){
                    return true;
                }
            }
        }
        //checa que asientos del Arreglo B1 estan seleccionados
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(this.frmElegirAsiento.getAsientosB1()[i][j].isSelected()){
                    return true;
                }
            }
        }
        //checa que asientos del Arreglo B2 estan seleccionados
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(this.frmElegirAsiento.getAsientosB2()[i][j].isSelected()){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void deseleccionarAsientos(){
        //asientosA
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.frmElegirAsiento.getAsientosA()[i][j].setSelected(false);
            }
        }
        
        //asientosC
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
        
        //asientosB2
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.frmElegirAsiento.getAsientosB2()[i][j].setSelected(false);
            }
        }
        this.precioTotal=0;
        this.frmElegirAsiento.getTxtPrecioPagar().setText("");
    }
    
    public void crearTicket(){
        try {
            this.ticket.setNombreObra(this.funcionActual.getObra().getNombre());
            this.ticket.setFechaVenta(this.funcionActual.getFechaPresentacion());
            this.ticket.setHorario(this.funcionActual.getHoraPresentacion());
            this.ticket.setBoletosVendidos(NombreAsientos);
            double total= this.precioTotal;
            this.ticket.setTotalVenta(total);
            double entregado=Double.parseDouble(this.frmElegirAsiento.getTxtMontoPagar().getText());
            this.ticket.setMontoEntregado(entregado);
            this.ticket.setCambio(entregado-total);
        } catch (Exception e) {
            System.out.println("error al crear ticket");
        }
    }
    
    public void desabilitarAsientosBD(){
        DAOSala daoSala= new DAOSala();
        System.out.println(NombreAsientos+ " " + this.funcionActual.getId());
        daoSala.ocuparAsiento(NombreAsientos, this.funcionActual.getId());        
    }
    
}
