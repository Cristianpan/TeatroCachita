package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class Db {

    private Connection myDbConn = null;
    
    // Se agrego la nueva base de datos
    public Connection getConexion(){
        try {
//            String url="jdbc:mysql://root:L28JEQ3krzHvVLJtk1IR@containers-us-west-114.railway.app:7595/railway"; 
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            this.myDbConn=DriverManager.getConnection(url);
            
            String url=""; 
            myDbConn=DriverManager.getConnection(url, "", "");
    
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

