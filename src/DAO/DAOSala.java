package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;

import Recursos.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.*;

public class DAOSala extends Db {
    
    public boolean crearNuevaSala (int idFuncion){
        boolean fueCreadoSala = false; 
        Connection con = getConexion(); 
        PreparedStatement ps = null; 
        String sql = "INSERT INTO sala (funcionId) VALUES (?)"; 


        try {
            ps =  con.prepareStatement(sql); 
            ps.setInt(1, idFuncion);
            ps.execute(); 

            fueCreadoSala = true; 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fueCreadoSala; 
    }
    
    public boolean ocuparAsiento(ArrayList<String> asiento, int idFuncion){
        boolean asientoOcupado= false;
        Connection con = getConexion(); 
        PreparedStatement ps = null;
        String set= "";
        for (int i = 0; i < asiento.size(); i++) {
            if(i == asiento.size()-1){
                set= set +" "+ asiento.get(i)+"=1 ";
            }else{
                set= set +" "+ asiento.get(i)+"=1,";
            }
        }
        String sql = "UPDATE sala SET " + set + "WHERE funcionId = " + idFuncion;
        
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            asientoOcupado = true;
        } catch (Exception ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return asientoOcupado;
    }
    
    public ArrayList<Integer> obtenerAsientos(int funcionId){
        ArrayList<Integer> asientos = new ArrayList<>(); 

        PreparedStatement ps; 

        ResultSet rs = null; 
        Connection con = getConexion(); 

        String sql = "SELECT * FROM sala WHERE funcionId = ?"; 

        try {
            ps = con.prepareStatement(sql); 
            ps.setInt(1, funcionId); 

            rs = ps.executeQuery(); 

            if (rs.next()){
                for (int i = 2; i <= 43; i++){
                    asientos.add(rs.getInt(i)); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return asientos; 
    }
    
}
