/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Recursos.Db;
import Modelo.Obra;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAOObra extends Db {

    public void agregarObra(Obra obra) throws SQLException {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO obra (nombre, genero, primerActor, segundoActor, precioBoleto, duracion, resumen) VALUES (?,?,?,?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setString(1, obra.getNombre());
        ps.setString(2, obra.getGenero());
        ps.setString(3, obra.getPrimerActor());
        ps.setString(4, obra.getSegundoActor());
        ps.setString(5, Double.toString(obra.getPrecioBoleto()));
        ps.setString(6, Double.toString(obra.getDuracion()));
        ps.setString(7, obra.getResumen());
        ps.execute();
    }

    public Obra buscarObra(String nombreObra) throws SQLException {
        Obra obra = null;
        PreparedStatement ps;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM obra WHERE nombre = ?";

        ps = con.prepareStatement(sql);
        ps.setString(1, nombreObra);
        rs = ps.executeQuery(); // trae resultados de la consulta

        if (rs.next()) {
            obra = new Obra();
            obra.setId(Integer.parseInt(rs.getString("id")));
            obra.setNombre(rs.getString("nombre"));
            obra.setPrimerActor(rs.getString("primerActor"));
            obra.setSegundoActor(rs.getString("segundoActor"));
            obra.setGenero(rs.getString("genero"));
            obra.setDuracion(Integer.parseInt(rs.getString("duracion")));
            obra.setPrecioBoleto(Double.parseDouble(rs.getString("precioBoleto")));

            obra.setResumen(rs.getString("resumen"));
        }

        if (con != null){
            con.close();
        }
        return obra;
    }

    public void actualizarObra(Obra obra) throws SQLException {
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "UPDATE obra SET nombre = ?, genero = ?, primerActor = ?, segundoActor = ?, precioBoleto = ?, duracion = ?, resumen = ? WHERE id = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, obra.getNombre());
        ps.setString(2, obra.getGenero());
        ps.setString(3, obra.getPrimerActor());
        ps.setString(4, obra.getSegundoActor());
        ps.setString(5, Double.toString(obra.getPrecioBoleto()));
        ps.setString(6, Double.toString(obra.getDuracion()));
        ps.setString(7, obra.getResumen());
        ps.setInt(8, obra.getId());
        ps.executeUpdate();

        if (con != null){
            con.close();
        }

    }

    public void eliminarObra(int idObra) throws SQLException {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM obra WHERE id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, idObra);

        ps.executeUpdate();

        if (con != null){
            con.close();
        }
    }

    public ArrayList<Obra> obtenerObrasRegistradas() throws SQLException {
        ArrayList<Obra> obras = new ArrayList<>();
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM obra";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Obra obra = new Obra();
            obra.setId(rs.getInt("id"));
            obra.setNombre(rs.getString("nombre"));
            obra.setGenero(rs.getString("genero"));
            obra.setDuracion(rs.getInt("duracion"));
            obra.setPrimerActor(rs.getString("primerActor"));
            obra.setSegundoActor(rs.getString("segundoActor"));
            obra.setPrecioBoleto(rs.getDouble("precioBoleto"));
            obra.setResumen(rs.getString("resumen"));
            obras.add(obra);
        }

        if (con != null){
            con.close();
        }

        return obras;
    }

}
