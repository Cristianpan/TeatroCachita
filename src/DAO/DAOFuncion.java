package DAO;

import java.sql.*;
import java.util.logging.*;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import java.util.ArrayList;

import Modelo.Funcion;
import Modelo.Obra;
import Recursos.Db;

public class DAOFuncion extends Db {

    public int agregarFuncion(Funcion funcion){
        int idNuevaFuncion = 0; 
        PreparedStatement ps = null; 
        Connection con = getConexion(); 
        String sql = "INSERT INTO funcion (obraId, fechaPresentacion, horaPresentacion) VALUES (?,?,?)";

        ResultSet result = null; 
        
        try {
            ps = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, funcion.getObra().getId());
            ps.setDate(2, funcion.getFechaPresentacion());
            ps.setTime(3, funcion.getHoraPresentacion());
            
            ps.executeUpdate();

            result = ps.getGeneratedKeys(); 

            if (result.next()){
                idNuevaFuncion = result.getInt(1);  
            } 

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return idNuevaFuncion; 
    }
    
    public boolean eliminarFuncion(int idFuncion) {
        boolean fueEliminado = false;
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM  funcion WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idFuncion);
            ps.executeUpdate();
            fueEliminado = true;

        } catch (Exception exception) {
            Logger.getLogger(DAOFuncion.class.getName()).log(Level.SEVERE, null, exception);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fueEliminado;
    }

    // No se tiene asociado ninguna funcionalidad a la funcion de consulta
    public void consultarFuncion() {
        
    }

    public ArrayList<Funcion> buscarPorFecha(Date fecha) {
        ArrayList<Funcion> funciones = new ArrayList<>();
        PreparedStatement ps;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM funcion INNER JOIN obra ON funcion.obraId = obra.id WHERE funcion.fechaPresentacion = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, fecha);
            rs = ps.executeQuery();

            while (rs.next()) {
                Funcion funcion = new Funcion();
                Obra obra = new Obra(); 
                funcion.setId(rs.getInt("id"));
                obra.setId(rs.getInt("obraId"));
                obra.setDuracion(rs.getInt("duracion"));
                obra.setNombre(rs.getString("nombre"));
                funcion.setObra(obra); 
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

    public ArrayList<Funcion> obtenerFuncionesRegistradas(){
        ArrayList<Funcion> funcionesRegistradas = new ArrayList<>(); 
        PreparedStatement ps;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM funcion INNER JOIN obra ON funcion.obraId = obra.id"; 
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Funcion funcion = new Funcion();
                Obra obra = new Obra(); 
                funcion.setId(rs.getInt("id"));
                obra.setId(rs.getInt("obraId"));
                obra.setNombre(rs.getString("nombre"));
                funcion.setObra(obra); 
                funcion.setFechaPresentacion(rs.getDate("fechaPresentacion"));
                funcion.setHoraPresentacion(rs.getTime("horaPresentacion"));

                funcionesRegistradas.add(funcion);
            }

            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return funcionesRegistradas; 
    }
    
    public boolean modificarFuncion(Funcion funcion) {
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "UPDATE funcion SET fechaPresentacion = ?, horaPresentacion = ?, obraId = ? WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, funcion.getFechaPresentacion());
            ps.setTime(2, funcion.getHoraPresentacion());
            ps.setInt(3, funcion.getObra().getId());
            ps.setInt(4, funcion.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public void consultarFechasFuncionesExistente() {
        Date date = null;
        PreparedStatement ps = null;
        ResultSet rs;
    }
}
