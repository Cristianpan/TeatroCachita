package DAO;

import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.protocol.Resultset;
import java.util.*;
import Modelo.*;
import Recursos.Db;

public class DAOFuncion extends Db {

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
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return funcionAgregada;
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

            while (rs.next()) {
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

    // Busca a una obra por su nombre y retorna en un int en a su key
    public int obraId(Funcion funcion) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "SELECT id FROM obra WHERE nombre = ?";
        ResultSet rs = null;
        int idObra = 0;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, funcion.getObraTeatral());

            rs = ps.executeQuery();
            idObra = ((Number) rs.getObject(1)).intValue();

            return idObra;

        } catch (Exception e) {
            e.printStackTrace();

            return idObra;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    }
    
    

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
    
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    
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
