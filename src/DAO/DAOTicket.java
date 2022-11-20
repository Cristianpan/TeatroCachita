/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Recursos.Db;
import java.sql.*;

/**
 *
 * @author diana
 */
public class DAOTicket extends Db{
    
    public int agregarTicket(Ticket ticket) {
        int idTicketAgregado = 0;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO ticket (fechaVenta, totalVenta, montoEntregado, cambio, asientos, horaVenta, nombreObra) VALUES (?,?,?,?,?,?,?)";
        ResultSet result=null;

        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, ticket.getFechaVenta());
            ps.setDouble(2, ticket.getTotalVenta());
            ps.setDouble(3, ticket.getMontoEntregado());
            ps.setDouble(4, ticket.getCambio());
            ps.setString(5, ticket.getBoletosVendidos().toString());
            ps.setTime(6, ticket.getHorario());
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
    
    public int obtenerID(){
        int ticketID=0;
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT numero FROM ticket";
        
        return ticketID;
    }
}
