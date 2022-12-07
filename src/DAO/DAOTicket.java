/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Ticket;
import Recursos.Db;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author diana
 */
public class DAOTicket extends Db{
    
    public int agregarTicket(Ticket ticket) {
        int idTicketAgregado = 0;
        String boletos = null; 
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO ticket (fechaVenta, totalVenta, montoEntregado, cambio, asientos, horaVenta, nombreObra) VALUES (?,?,?,?,?,?,?)";
        ResultSet result = null;
        
        for (String boleto : ticket.getBoletosVendidos()) {
            boletos = boleto + "," + boleto; 
        }

        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, ticket.getFechaVenta());
            ps.setDouble(2, ticket.getTotalVenta());
            ps.setDouble(3, ticket.getMontoEntregado());
            ps.setDouble(4, ticket.getCambio());

            ps.setString(5, boletos);
            ps.setTime(6, ticket.getHoraVenta());
            ps.setString(7, ticket.getNombreObra());
            ps.executeUpdate();
            
            result = ps.getGeneratedKeys();
            if (result.next()) {
                idTicketAgregado= result.getInt(1);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return idTicketAgregado;
    }
    

    public ArrayList<Ticket> obtenerTickets(String fecha){
        ArrayList<Ticket> tickets = new ArrayList<>(); 
        PreparedStatement ps; 
        ResultSet rs = null; 
        Connection con = getConexion(); 
        
        String sql = "SELECT * FROM ticket WHERE fechaVenta like \"" + fecha + "%\""; 
        
        try {
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            
            while (rs.next()){
                Ticket ticket = new Ticket(); 
                ArrayList<String> boletosVendidos = new ArrayList<>(); 
                ticket.setNumVenta(rs.getInt("numero"));
                ticket.setFechaVenta(rs.getDate("fechaVenta"));
                ticket.setTotalVenta(rs.getDouble("totalVenta"));
                ticket.setNombreObra(rs.getString("nombreObra"));
                boletosVendidos.add(rs.getString("asientos")); 
                ticket.setBoletosVendidos(boletosVendidos);

                tickets.add(ticket);             }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return tickets; 
    }
}
