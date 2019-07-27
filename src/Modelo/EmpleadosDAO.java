/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ludgarys
 */
public class EmpleadosDAO extends Conexion {
      public String insertar(String Cedula,String Nombre,String Apellido,String Direccion,String Telefono){
       String rptaRegistro=null;
       PreparedStatement cs=null;
       Connection con= obtenerConexion();
       String sql="INSERT INTO colaboradores (Cedula,Nombre,Apellido,Dirección,Teléfono)VALUE(?,?,?,?,?)";
       try{
           cs=con.prepareStatement(sql);
          
           cs.setString(1,Cedula);
           cs.setString(2,Nombre);
           cs.setString(3,Apellido);
           cs.setString(4,Direccion);
           cs.setString(5,Telefono);
           
           int numFAfectas=cs.executeUpdate();
           if(numFAfectas>0){
               rptaRegistro="Registro exitoso";
                
           }
           
           
       }catch(SQLException ex){
            System.err.println(ex);
           
       }
       return rptaRegistro;    
   }
   
 
   public ArrayList<RegistrarEmp> listar() {
       ArrayList listapersona=new ArrayList();
       RegistrarEmp persona;
       Connection con= obtenerConexion();
       try{
           
           PreparedStatement ps=con.prepareStatement("SELECT Cedula,Nombre,Apellido,Dirección,Teléfono FROM colaboradores");
           ResultSet rs= ps.executeQuery();
           while(rs.next()){               //registros por leer
               persona =new RegistrarEmp ();
               persona.setCedula(rs.getString(1));
               persona.setNombre(rs.getString(2));
               persona.setApellido(rs.getString(3));
               persona.setDireccion(rs.getString(4));
               persona.setTelefono(rs.getString(5));
               listapersona.add(persona);
               }
       }catch(SQLException ex){
        System.err.println(ex.toString());
          
           
       }
           return listapersona;
   }
   
   
   public int EditarProducto(String Cedula,String Nombre,String Apellido, String Direccion,String Telefono) {
       int numFA=0;
       try{
           Connection con= obtenerConexion();
           PreparedStatement cs=null;
           String sql="UPDATE colaboradores SET Cedula=?,Nombre=?,Apellido=?, Dirección=?, Teléfono=? WHERE ID=?";
           cs.setString(1,Cedula);
           cs.setString(2,Nombre);
           cs.setString(3,Apellido);
           cs.setString(4,Direccion);
           cs.setString(5,Telefono);
           
          
           
           numFA=cs.executeUpdate();
           
           }catch(Exception e){
       }
         return numFA;
   }
   
   public int EliminarProducto(String Codigo){
       int numFA=0;
           
           try{
           PreparedStatement cs=null;
           Connection con= obtenerConexion();
           String sql="DELETE FROM colaboradores WHERE Cedula=?";
           cs=con.prepareStatement(sql);
           cs.setString(1, Codigo);
            
           
           numFA=cs.executeUpdate();
           
           }catch(SQLException ex){
            System.err.println(ex);
            
        }
        finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.err.println(ex);
            }
               
       }
            return numFA;
   }
   
   public ArrayList<RegistrarEmp>BuscarNombre(String Nombre){
       ArrayList<RegistrarEmp>listapersona=new ArrayList();
       RegistrarEmp persona;
       
       try{
            Connection con= obtenerConexion();
             PreparedStatement cs=null;
            
           String sql="SELECT *FROM colaboradores WHERE Nombre=?";
           cs=con.prepareStatement(sql);
           cs.setString(1, Nombre);
           ResultSet rs=cs.executeQuery();
           while(rs.next()){
              persona =new RegistrarEmp ();
              persona.setCedula(rs.getString(1));
              persona.setNombre(rs.getString(2));
              persona.setApellido(rs.getString(3));
              persona.setDireccion(rs.getString(4));
              persona.setTelefono(rs.getString(5));
               listapersona.add(persona);
               
           }
           
       
        }
        catch(Exception e){
            
            
   }
       return listapersona;
       
   }
  
}
