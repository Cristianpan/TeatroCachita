package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Recursos.*;

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
    
}
