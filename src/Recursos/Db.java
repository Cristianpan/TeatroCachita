package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class Db {

    private Connection con = null; 

    public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection( "jdbc:mysql://us-east.connect.psdb.cloud/chachitadb?sslMode=VERIFY_IDENTITY",
            "xsrxautxbwklivdgrxd9",
            "pscale_pw_xNhsHD8m9qEMAXJFc89aVPXvcp3Z5EGbObbazZ4u3zi");
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Asegurese de tener una conexión a la red");
        }

        return this.con;
    }

}

