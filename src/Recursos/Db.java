package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class Db {

    private Connection con = null; 

    public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection( "");
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Asegurese de tener una conexión a la red");
        }

        return this.con;
    }

}

