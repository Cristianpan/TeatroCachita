package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class Db {

    private Connection con = null; 

    public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://cecatidb.mysql.database.azure.com:3306/cachita?useSSL=true&requireSSL=false",
            "AbrahamXTS@cecatidb", 
            "Hey?llego?el?sensei");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegurese de tener una conexión a la red");
        }

        return this.con;
    }
}

