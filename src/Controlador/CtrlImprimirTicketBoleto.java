/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAOTicket;
import Modelo.*;
import Vista.ImprimirTicketBoletos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author diana
 */
public class CtrlImprimirTicketBoleto implements ActionListener{
    private Ticket ticket;
    private ImprimirTicketBoletos frmImprimirTicketBoleto;
    private Funcion funcion;
    
    private int indiceBoletoActual=0;
    private int indiceBoletoFinal=0;

    public CtrlImprimirTicketBoleto(Ticket ticket, ImprimirTicketBoletos frmImprimirTicketBoleto, Funcion funcion) {
        this.ticket = ticket;
        this.frmImprimirTicketBoleto = frmImprimirTicketBoleto;
        this.funcion = funcion;        
        agregarBoletoInicial(this.frmImprimirTicketBoleto);
        agregarTicket(this.frmImprimirTicketBoleto);
        this.indiceBoletoFinal= this.ticket.getBoletosVendidos().size()-1;
        this.frmImprimirTicketBoleto.getBtnRegresar().addActionListener(this);
        this.frmImprimirTicketBoleto.getBtnSiguiente().addActionListener(this);
        this.frmImprimirTicketBoleto.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.frmImprimirTicketBoleto.getBtnSiguiente()){
            try {
                agregarBoleto();
            } catch (Exception ex) {
                System.out.println("algo salió mal " + ex);
            }
               
        }
        
        if(e.getSource() == this.frmImprimirTicketBoleto.getBtnRegresar()){
            //regresar a elegir funcion   
        }
    }
    
    public void agregarBoleto(){
        indiceBoletoActual++;
        if(indiceBoletoActual>indiceBoletoFinal){
            indiceBoletoActual=0;
        }
        this.frmImprimirTicketBoleto.getTxtAsiento().setText(this.ticket.getBoletosVendidos().get(indiceBoletoActual).toString());
    }
    
    public void agregarBoletoInicial(ImprimirTicketBoletos impB){
        impB.getTxtNombreObra().setText(this.ticket.getNombreObra());
        impB.getTxtFecha().setText(this.ticket.getFechaVenta().toString());
        impB.getTxtHora().setText(this.ticket.getHorario().toString());
        impB.getTxtAsiento().setText(this.ticket.getBoletosVendidos().get(0));
    }
    
    public void agregarTicket(ImprimirTicketBoletos impB){
        aniadirTicketBD();
        String id= "Num. Venta: " +this.ticket.getNumVenta()+"\n";
        String nombreObra= "Función: " + this.ticket.getNombreObra() + "\n";
        String fecha= "Fecha: " +this.ticket.getFechaVenta().toString()+ "\n";
        String horario= "Horario: " +this.ticket.getHorario().toString()+ "\n";
        String asiento= "Asientos: " +this.ticket.getBoletosVendidos()+ "\n"+ "\n";
        
        String precioTotal= "PrecioTotal: " + this.ticket.getTotalVenta()+ "\n";
        String recibido= "Monto entregado: " + this.ticket.getMontoEntregado()+ "\n";
        String cambio= "Cambio: " + this.ticket.getCambio()+ "\n";
        impB.getTxtTicket().setText(id+nombreObra+fecha+horario+asiento+precioTotal+recibido+cambio);
    }
    
    public void aniadirTicketBD(){
        DAOTicket daoTicket= new DAOTicket();
        int numVenta=daoTicket.agregarTicket(this.ticket);
        if(numVenta != 0){
            System.out.println("ticket guardado con éxito");
            this.ticket.setNumVenta(numVenta);
        }else{
            System.out.println("error al guardar ticket");
        }
    }
            
}
