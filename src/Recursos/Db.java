
package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Db {

    private Connection myDbConn = null;

    public Connection getConexion() {
        try {
            String url = "";
            myDbConn = DriverManager.getConnection(url, "", "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegurese de tener una conexión a la red");
        }

        return this.myDbConn;
    }
}