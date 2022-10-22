package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Recursos.Db;


public class ConsultaUsuario extends Db {


    public boolean isLogin(User user){
        boolean isLogin = false; 
        PreparedStatement ps = null; 
        Connection con = getConexion(); 
        ResultSet rs = null; 
        
        String sql = "SELECT * FROM usuario WHERE nombreUsuario = ? and contrasena = ?"; 

        try {
            ps = con.prepareStatement(sql); 
            ps.setString(1, user.getNombreUsuario());
            ps.setString(2, user.getContrasena());
            rs = ps.executeQuery(); 

            if(rs.next()){
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setNombreUsuario(rs.getString("nombreUsuario"));
                user.setTipo(rs.getString("tipo"));
                user.setContrasena(rs.getString("contrasena"));
                isLogin = true; 
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try{
                con.close();
            } catch(Exception e){
                System.out.println(e);
            }
        }

        return isLogin; 
    }

    public boolean isRegister(User user){
        boolean isRegister = false; 
        PreparedStatement ps = null; 
        Connection con = getConexion(); 
        String sql = "INSERT INTO usuario (nombre, apellido, nombreUsuario, tipo, curp, contrasena) VALUES (?,?,?,?,?,?)"; 

        try {
            ps = con.prepareStatement(sql); 
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setString(3, user.getNombreUsuario());
            ps.setString(4, user.getTipo());
            ps.setString(5, user.getCurp());
            ps.setString(6, user.getContrasena());

            ps.execute();
            
            isRegister = true; 
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try{
                con.close();
            } catch(Exception e){
                System.out.println(e);
            }
        }
        return isRegister; 
    }

    public User buscarUsuario(String nombreUsuario){
        User user = null; 
        PreparedStatement ps;
        Connection con = getConexion(); 
        ResultSet rs = null; 
        
        String sql = "SELECT * FROM usuario WHERE nombreUsuario = ?"; 
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery(); 
            if(rs.next()){
                user = new User(); 
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setNombre(rs.getString("nombre")); 
                user.setApellido(rs.getString("apellido"));
                user.setNombreUsuario(rs.getString("nombreUsuario"));
                user.setTipo("tipo");
                user.setContrasena(rs.getString("contrasena"));
                user.setCurp(rs.getString("curp")); 
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try{
                con.close();
            } catch(Exception e){
                System.out.println(e);
            }
        }

        return user; 
    }


}
