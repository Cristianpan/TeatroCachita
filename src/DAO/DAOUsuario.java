package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Modelo.User;
import Recursos.Db;

public class DAOUsuario extends Db {

    public boolean isLogin(User user) {
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

            if (rs.next()) {
                user.setTipo(rs.getString("tipo"));
                isLogin = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return isLogin;
    }

    public boolean isRegister(User user) {
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

        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isRegister;
    }

    public User buscarUsuario(String nombreUsuario) {
        User user = null;
        PreparedStatement ps;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM usuario WHERE nombreUsuario = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setNombreUsuario(rs.getString("nombreUsuario"));
                user.setTipo(rs.getString("tipo"));
                user.setContrasena(rs.getString("contrasena"));
                user.setCurp(rs.getString("curp"));
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public boolean actualizarUsuario(User user) {
        boolean fueActualizado = false;
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, nombreUsuario = ?, tipo = ?, contrasena = ?, curp = ? WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setString(3, user.getNombreUsuario());
            ps.setString(4, user.getTipo());
            ps.setString(5, user.getContrasena());
            ps.setString(6,  user.getCurp());
            ps.setInt(7, user.getId());

            ps.executeUpdate(); 
            fueActualizado = true;

        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fueActualizado;
    }

    public boolean eliminarUsuario(int idUsuario){
        boolean fueEliminado = false; 
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ps.executeUpdate(); 
            fueEliminado = true; 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fueEliminado; 
    }

}
