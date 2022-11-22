package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class Db {

    private Connection myDbConn = null;
    
    String url="jdbc:mysql://localhost:3306/chachita";
    // Se agrego la nueva base de datos
    public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this. myDbConn=DriverManager.getConnection(url, "root", "Gatosinbotas1");
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegurese de tener una conexión a la red");
        }

        return this.myDbConn;
    }


    public static void main(String[] args) {
        Db db = new Db();
        
        System.out.println( db.getConexion());
       

    }
}

