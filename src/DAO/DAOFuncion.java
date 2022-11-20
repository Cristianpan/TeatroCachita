package DAO;

import java.sql.*;
import java.util.logging.*;
<<<<<<< HEAD
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.protocol.Resultset;
import java.util.*;
import Modelo.*;
=======

import java.util.ArrayList;

import Modelo.Funcion;
import Modelo.Obra;
>>>>>>> 1a54b4c9437f67878b638242bd0ebd2b602113f7
import Recursos.Db;

public class DAOFuncion extends Db {

<<<<<<< HEAD
    public boolean agregarFuncion(Funcion funcion, int idObra) {
        boolean funcionAgregada = false;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO funcion (obraId, fechaPresentacion, horaPresentacion) VALUES (?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idObra);
            ps.setDate(2, funcion.getFechaPresentacion());
            ps.setTime(3, funcion.getHoraPresentacion());
            ps.execute();

            funcionAgregada = true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
=======
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
>>>>>>> 1a54b4c9437f67878b638242bd0ebd2b602113f7
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

    public boolean modificarFuncion(Funcion funcion, Funcion funcionModificada) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE funcion SET fechaPresentacion = ?, horaPresentacion = ?, obraId = ? WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, funcionModificada.getFechaPresentacion());
            ps.setTime(2, funcionModificada.getHoraPresentacion());

            // Buscar el id de la obra en su tabla y usarlo para modificar la obra en la
            // tabla de funcion
            int idObraModificada = this.obraId(funcionModificada);
            ps.setInt(3, idObraModificada);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al modificar la función");
            return false;
        }
    }

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

<<<<<<< HEAD
            return idObra;
=======
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOObra.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> 1a54b4c9437f67878b638242bd0ebd2b602113f7
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
    }
    
    // Esta funcion retorna una obra con todos sus datos desde la base de datos
    public ResultSet getDatosObra(int idObra) {
        Connection con = getConexion();
        PreparedStatement ps;
        ResultSet rs = null; // Falta debuguear que ocurre cuando no existe el id de la obra que se pide
        String sql = "SELECT nombre, genero, primerActor, duracion WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idObra);
            rs = ps.executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return rs;
=======

        return funcionesRegistradas; 
>>>>>>> 1a54b4c9437f67878b638242bd0ebd2b602113f7
    }
    
    

<<<<<<< HEAD
    /*
    public Funcion regresarDatosEnCasillas(JTable tblObra) {
        Funcion funcion = new Funcion();
    
        try {
    
            int fila = tblObra.getSelectedRow();
            int id = Integer.parseInt(tblObra.getValueAt(fila, 5).toString());
    
            PreparedStatement ps;
            ResultSet rs;
            Connection conexion = getConexion();
    
            ps = conexion.prepareStatement(
                    "SELECT nombre, genero, primerActor, segundoActor, precioBoleto, duracion, resumen FROM obra WHERE id = ?");
            ps.setInt(1, id);
    
            // Ejecutar consulta
            rs = ps.executeQuery();
    
            while (rs.next()) {
                obra.setID(id);
                obra.setNombreObra(rs.getString("nombre"));
                obra.setGenero(rs.getString("genero"));
                obra.setResumen(rs.getString("resumen"));
                obra.setDuracion(rs.getInt("duracion"));
                obra.setActorPrincipal(rs.getString("actorPrincipal"));
                obra.setActorSecundario(rs.getString("actorSecundario"));
                obra.setPrecioBoleto(rs.getDouble("precioBoleto"));
    
=======
    public boolean modificarFuncion(Funcion funcion){
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
>>>>>>> 1a54b4c9437f67878b638242bd0ebd2b602113f7
            }
        }
<<<<<<< HEAD
    
        return funcion;
    } */
    
    // Cargara los datos de funciones en la jtable de la vista con ayuda del controlador
    // No terminado
    public JTable cargarTabla(JTable tblFunciones) {
        DefaultTableModel modtabla = (DefaultTableModel) tblFunciones.getModel();
        PreparedStatement ps;
        Resultset rs;
        ResultSetMetaData rsMd;
        Connection con = getConexion();
        String sql = "SELECT funcion id = ?";

        modtabla.setRowCount(0);
        int columnas;

        try {
            ps = con.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return tblFunciones;
    }
	
}
=======
        return false; 
    }
}
>>>>>>> 1a54b4c9437f67878b638242bd0ebd2b602113f7
