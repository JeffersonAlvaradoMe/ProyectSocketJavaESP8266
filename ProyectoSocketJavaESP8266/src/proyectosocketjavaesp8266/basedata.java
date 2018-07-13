/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosocketjavaesp8266;

//import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class basedata {
    Connection cn;
    public Connection conectar()  {
        try  {
        Class.forName("com.mysql.jdbc.Driver");
            cn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/prueba_tienda","root","");
       
        }

catch ( ClassNotFoundException | SQLException ex){
    System.out.println("Se ha encontrado el siguiente error"+ex.getMessage());
    
    }
        return cn;
    
     
}
    }
