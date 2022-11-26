package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Db {

    private Connection myDbConn = null;

    public Connection getConexion() {
        try {
            String url = "jdbc:mysql://chachitauady.mysql.database.azure.com:3306/chachita?useSSL=true";
            myDbConn = DriverManager.getConnection(url, "CristianPan", "Chachita1");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegurese de tener una conexión a la red");
        }

        return this.myDbConn;
    }
}
