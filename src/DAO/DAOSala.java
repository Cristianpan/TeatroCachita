package dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;

import Recursos.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOSala extends Db {
    
    public void crearNuevaSala (int idFuncion) throws SQLException{
        Connection con = getConexion(); 
        PreparedStatement ps = null; 
        String sql = "INSERT INTO sala (funcionId) VALUES (?)"; 


        ps =  con.prepareStatement(sql); 
        ps.setInt(1, idFuncion);
        ps.execute(); 

        if (con != null){
            con.close();
        }
 
    }
    
    public void ocuparAsiento(ArrayList<String> asiento, int idFuncion) throws SQLException{
       
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
        ps = con.prepareStatement(sql);
        ps.executeUpdate();

        
        if(con != null){
            con.close();
        }
    }
    
    public ArrayList<Integer> obtenerAsientos(int funcionId) throws SQLException{
        ArrayList<Integer> asientos = new ArrayList<>(); 

        PreparedStatement ps; 

        ResultSet rs = null; 
        Connection con = getConexion(); 

        String sql = "SELECT * FROM sala WHERE funcionId = ?"; 
        ps = con.prepareStatement(sql); 
        ps.setInt(1, funcionId); 

        rs = ps.executeQuery(); 

        if (rs.next()){
            for (int i = 2; i <= 43; i++){
                asientos.add(rs.getInt(i)); 
            }
        }

        if (con != null){
            con.close();
        }

        return asientos; 
    }
    
}
