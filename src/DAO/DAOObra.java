/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Obra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Recursos.Db;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diana
 */
public class DAOObra extends Db{
    
    public boolean agregarObra(Obra obra){
        boolean obraAgregada= false;
        PreparedStatement ps = null; 
        Connection con = getConexion(); 
        String sql = "INSERT INTO obra (nombre, genero, primerActor, segundoActor, precioBoleto, duracion, resumen) VALUES (?,?,?,?,?,?,?)";
        
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, obra.getNombre());
            ps.setString(2, obra.getGenero());
            ps.setString(3, obra.getPrimerActor());
            ps.setString(4, obra.getSegundoActor());
            ps.setString(5, Double.toString(obra.getPrecioBoleto()));
            ps.setString(6, Double.toString(obra.getDuracion()));
            ps.setString(7, obra.getResumen());
            ps.execute();
            obraAgregada=true;
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return obraAgregada;
    }
    
    public Obra buscarObra(String nombreObra){
        Obra obra= null;
        PreparedStatement ps;
        Connection con = getConexion(); 
        ResultSet rs = null; 
        
        String sql = "SELECT * FROM obra WHERE nombre = ?"; 
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreObra);
            rs = ps.executeQuery(); //trae resultados de la consulta
            
            if(rs.next()){
                obra = new Obra(); 
                obra.setId(Integer.parseInt(rs.getString("id")));
                obra.setNombre(rs.getString("nombre")); 
                obra.setPrimerActor(rs.getString("primerActor"));
                obra.setSegundoActor(rs.getString("segundoActor"));
                obra.setGenero(rs.getString("genero"));
                obra.setDuracion(Double.parseDouble(rs.getString("duracion")));
                obra.setPrecioBoleto(Double.parseDouble(rs.getString("precioBoleto")));
                 
                obra.setResumen(rs.getString("resumen")); 
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obra;
    }
    
    public boolean actualizarObra(Obra obra){
        boolean fueActualizado = false;
        PreparedStatement ps;
        Connection con = getConexion();
        
        String sql = "UPDATE obra SET nombre = ?, genero = ?, primerActor = ?, segundoActor = ?, precioBoleto = ?, duracion = ?, resumen = ? WHERE id = ?";
        
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, obra.getNombre());
            ps.setString(2, obra.getGenero());
            ps.setString(3, obra.getPrimerActor());
            ps.setString(4, obra.getSegundoActor());
            ps.setString(5, Double.toString(obra.getPrecioBoleto()));
            ps.setString(6, Double.toString(obra.getDuracion()));
            ps.setString(7, obra.getResumen());
            ps.setInt(8, obra.getId());
            
            ps.executeUpdate();
            fueActualizado= true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
               con.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            
        return fueActualizado;
    }
    
    public boolean eliminarUsuario(String nombre){
        boolean fueEliminado = false; 
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM obra WHERE nombre = ?";
        
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, nombre);
            
            ps.executeUpdate();
            fueEliminado= true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
        return fueEliminado;
    }
    
    public ArrayList obrasRegistradas(){
        ArrayList<Obra> obras= new ArrayList<>();
        PreparedStatement ps;
        Connection con = getConexion(); 
        ResultSet rs = null; 
        
       String sql = "SELECT * FROM obra";
       
        try {
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            
            while(rs.next()){
               Obra obra= new Obra();
               obra.setId(rs.getInt("id"));
               obra.setNombre(rs.getString("nombre"));
               obra.setGenero(rs.getString("genero"));
               obra.setDuracion(rs.getDouble("duracion"));
               obra.setPrimerActor(rs.getString("primerActor"));
               obra.setSegundoActor(rs.getString("segundoActor"));
               obra.setPrecioBoleto(rs.getDouble("precioBoleto"));
               obra.setResumen(rs.getString("resumen"));
               obras.add(obra);
            }
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obras;
    }
    
}
