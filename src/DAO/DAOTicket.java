/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Recursos.Db;
import modelo.Ticket;

import java.sql.*;
import java.util.ArrayList;

public class DAOTicket extends Db {

    public int agregarTicket(Ticket ticket) throws SQLException {
        int idTicketAgregado = 0;
        String boletos = null;
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO ticket (fechaVenta, totalVenta, montoEntregado, cambio, asientos, horaVenta, nombreObra) VALUES (?,?,?,?,?,?,?)";
        ResultSet result = null;

        for (String boleto : ticket.getBoletosVendidos()) {
            boletos = boleto + "," + boleto;
        }

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
            idTicketAgregado = result.getInt(1);
        }

        if (con != null){
            con.close();
        }
        return idTicketAgregado;
    }

    public ArrayList<Ticket> obtenerTickets(String fecha) throws SQLException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM ticket WHERE fechaVenta like \"" + fecha + "%\"";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Ticket ticket = new Ticket();
            ArrayList<String> boletosVendidos = new ArrayList<>();
            ticket.setNumVenta(rs.getInt("numero"));
            ticket.setFechaVenta(rs.getDate("fechaVenta"));
            ticket.setTotalVenta(rs.getDouble("totalVenta"));
            ticket.setNombreObra(rs.getString("nombreObra"));
            boletosVendidos.add(rs.getString("asientos"));
            ticket.setBoletosVendidos(boletosVendidos);

            tickets.add(ticket);
        }

        if (con != null){
            con.close();
        }

        return tickets;
    }
}
