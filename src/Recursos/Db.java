package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class Db {

    private Connection myDbConn = null;
    String url ="jdbc:mysql://cecatidb.mysql.database.azure.com:3306/cachita?useSSL=true&requireSSL=false";
    


    public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.myDbConn = DriverManager.getConnection(url, "AbrahamXTS@cecatidb", "Hey?llego?el?sensei");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegurese de tener una conexión a la red");
        }

        return this.myDbConn;
    }

}

