/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Ludgarys
 */
public class Conexion {
      public  static String BASEDATOS="distribuidoradecafe";
   public static String USUARIO ="root";
   public  static String PASSWORD="" ;
   public  static String URL= "jdbc:mariadb://localhost:3306/"+BASEDATOS; 
   Connection con= null;
    
    public Connection obtenerConexion (){
        
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            con=(Connection)DriverManager.getConnection(this.URL,this.USUARIO,this.PASSWORD);
           
            
        }catch(SQLException ex){
            System.err.println(ex);
        }catch(ClassNotFoundException ex){
            System.err.println(ex);
        }
        return con;
    }
    
    public static String getUs(){
        return USUARIO;
    }
    
    public static String getPass(){
        return PASSWORD;
    }
    
    public static String getBDD(){
        return BASEDATOS;
    }
}
