package DAO;

import java.sql.*;
import java.util.logging.*;
import java.util.ArrayList;

import Modelo.Funcion;
import Recursos.Db;

public class DAOFuncion extends Db {

    public boolean agregarFuncion(Funcion funcion, int idObra){
        boolean funcionAgregada= false;
        PreparedStatement ps = null; 
        Connection con = getConexion(); 
        String sql = "INSERT INTO funcion (obraId, fechaPresentacion, horaPresentacion) VALUES (?,?,?)";
        
        try {
            ps= con.prepareStatement(sql);
            ps.setInt(1, idObra);
            ps.setDate(2, funcion.getFechaPresentacion());
            ps.setTime(3, funcion.getHoraPresentacion());
            ps.execute();
            
            funcionAgregada = true;
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return funcionAgregada;
    }

	public ArrayList<Funcion> buscarPorFecha(String fecha) {
		ArrayList<Funcion> funciones = new ArrayList<>();
        PreparedStatement ps;
        Connection con = getConexion(); 
        ResultSet rs = null; 
        
       String sql = "SELECT * FROM funcion INNER JOIN obra ON funcion.obraId = obra.id WHERE funcion.fechaPresentacion = ?";
       
        try {
            ps = con.prepareStatement(sql);
			ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            while(rs.next()){
            	Funcion funcion = new Funcion();
               	funcion.setId(rs.getInt("id"));
               	funcion.setObraTeatral(rs.getString("nombre"));
				funcion.setFechaPresentacion(rs.getDate("fechaPresentacion"));
				funcion.setHoraPresentacion(rs.getTime("horaPresentacion"));
               	
				funciones.add(funcion);
            }
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               con.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return funciones;
	}
	
}
