package dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;

import Recursos.Db;
import excepciones.ExcepcionDAOFunciones;
import modelo.Funcion;
import modelo.Obra;

public class DAOFuncion extends Db {

    public int agregarFuncion(Funcion funcion) throws SQLException{
        int idNuevaFuncion = 0;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO funcion (obraId, fechaPresentacion, horaPresentacion) VALUES (?,?,?)";

        ResultSet result = null;

        ps = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, funcion.getObra().getId());
        ps.setDate(2, funcion.getFechaPresentacion());
        ps.setTime(3, funcion.getHoraPresentacion());

        ps.executeUpdate();

        result = ps.getGeneratedKeys();

        if (result.next()) {
            idNuevaFuncion = result.getInt(1);
        }
        
        if (con != null){
            con.close();
        }
        return idNuevaFuncion;
    }

    public void eliminarFuncion(int idFuncion) throws SQLException {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM  funcion WHERE id = ?";
        
        ps = con.prepareStatement(sql);
        ps.setInt(1, idFuncion);
        ps.executeUpdate();
        
        if (con != null){
            con.close();
        }
    }

    public ArrayList<Funcion> obtenerFuncionPorFecha(Date fecha) throws SQLException {
        ArrayList<Funcion> funciones = new ArrayList<>();
        PreparedStatement ps;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM funcion INNER JOIN obra ON funcion.obraId = obra.id WHERE funcion.fechaPresentacion = ?";

        ps = con.prepareStatement(sql);
        ps.setDate(1, fecha);
        rs = ps.executeQuery();

        while (rs.next()) {
            Funcion funcion = new Funcion();
            Obra obra = new Obra();
            // Obtencion de datos de la obra
            obra.setId(rs.getInt("obraId"));
            obra.setNombre(rs.getString("nombre"));
            obra.setGenero(rs.getString("genero"));
            obra.setPrimerActor(rs.getString("primerActor"));
            obra.setSegundoActor(rs.getString("segundoActor"));
            obra.setDuracion(rs.getInt("duracion"));
            obra.setPrecioBoleto(rs.getDouble("precioBoleto"));
            obra.setResumen(rs.getString("resumen"));
            // Obtencion de datos de la funcion
            funcion.setObra(obra);
            funcion.setId(rs.getInt("id"));
            funcion.setFechaPresentacion(rs.getDate("fechaPresentacion"));
            funcion.setHoraPresentacion(rs.getTime("horaPresentacion"));

            funciones.add(funcion);
        }
        con.close();

        return funciones;
    }

    public ArrayList<Funcion> obtenerFuncionesRegistradas() throws SQLException {
        ArrayList<Funcion> funcionesRegistradas = new ArrayList<>();
        PreparedStatement ps;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM funcion INNER JOIN obra ON funcion.obraId = obra.id";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Funcion funcion = new Funcion();
            Obra obra = new Obra();
            // Obtencion de datos de la obra
            obra.setId(rs.getInt("obraId"));
            obra.setNombre(rs.getString("nombre"));
            obra.setGenero(rs.getString("genero"));
            obra.setPrimerActor(rs.getString("primerActor"));
            obra.setSegundoActor(rs.getString("segundoActor"));
            obra.setDuracion(rs.getInt("duracion"));
            obra.setPrecioBoleto(rs.getDouble("precioBoleto"));
            obra.setResumen(rs.getString("resumen"));
            // Obtencion de datos de la funcion
            funcion.setId(rs.getInt("id"));
            funcion.setObra(obra);
            funcion.setFechaPresentacion(rs.getDate("fechaPresentacion"));
            funcion.setHoraPresentacion(rs.getTime("horaPresentacion"));

            funcionesRegistradas.add(funcion);
        }

        con.close();
        

        return funcionesRegistradas;
    }

    public void modificarFuncion(Funcion funcion) throws SQLException {
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "UPDATE funcion SET fechaPresentacion = ?, horaPresentacion = ?, obraId = ? WHERE id = ?";

        ps = con.prepareStatement(sql);
        ps.setDate(1, funcion.getFechaPresentacion());
        ps.setTime(2, funcion.getHoraPresentacion());
        ps.setInt(3, funcion.getObra().getId());
        ps.setInt(4, funcion.getId());
        ps.executeUpdate();
        
        if (con != null){
            con.close();
        }
    }

    public void obtenerFuncionesVigentes(int idObra) throws ExcepcionDAOFunciones, SQLException {
        boolean funcionesVigentes = false;

        PreparedStatement ps;
        Connection con = getConexion();
        ResultSet rs = null;

        // Fecha actual
        Calendar dateToday = Calendar.getInstance();
        Date fecha = new Date(dateToday.getTimeInMillis());
        String fechaSql = fecha.getYear() + "-" + fecha.getMonth() + "-" + fecha.getDay(); 

        // Query
        String sql = "SELECT * FROM funcion JOIN obra ON obra.id = funcion.obraId WHERE fechaPresentacion >= \"" + fechaSql + "\" AND obra.id = ? ORDER BY fechaPresentacion ASC";
        ps = con.prepareStatement(sql);
        ps.setInt(1, idObra);

        rs = ps.executeQuery();

        while (rs.next()) {
            funcionesVigentes = true;
        }

        if (con != null){
            con.close();
        }

        if (funcionesVigentes == true) {
            throw new ExcepcionDAOFunciones("No se puede realizar esta acción, ya tiene funciones existentes");
        }
    }
}
