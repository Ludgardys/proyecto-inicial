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
public class ProductosDAO extends Conexion{
     public String insertar(String Código,String Nombre,Double Precio,int Cantidad){
       String rptaRegistro=null;
        PreparedStatement cs=null;
        Connection con= obtenerConexion();
        String sql="INSERT INTO productos (Código,Nombre,Precio,Cantidad)VALUE(?,?,?,?)";
       try{
      cs=con.prepareStatement(sql);
          
           cs.setString(1,Código);
           cs.setString(2,Nombre);
           cs.setDouble(3,Precio);
           cs.setInt(4,Cantidad);
           
           int numFAfectas=cs.executeUpdate();
           if(numFAfectas>0){
               rptaRegistro="Registro exitoso";
                
           }
           
           
       }catch(SQLException ex){
            System.err.println(ex);
           
       }
       return rptaRegistro;    
   }
   
 
   public ArrayList<Productos> listar() {
       ArrayList listaproducto=new ArrayList();
       Productos producto;
       Connection con= obtenerConexion();
       try{
           
           PreparedStatement ps=con.prepareStatement("SELECT Código,Nombre,Precio,Cantidad FROM productos");
           ResultSet rs= ps.executeQuery();
           while(rs.next()){               //registros por leer
               producto =new Productos ();
               producto.setCodigo(rs.getString(1));
               producto.setNombre(rs.getString(2));
               producto.setPrecio(rs.getDouble(3));
               producto.setCantidad(rs.getInt(4));
               listaproducto.add(producto);
               }
       }catch(SQLException ex){
        System.err.println(ex.toString());
          
           
       }
           return listaproducto;
   }
   
   
   public int EditarProducto(String Código,String Nombre,Double Precio,int Cantidad) {
       int numFA=0;
       try{
           Connection con= obtenerConexion();
           PreparedStatement cs=null;
           String sql="UPDATE productos SET Código=?,Nombre=?,Precio=?, Cantidad=? WHERE ID=?";
           cs.setString(1,Código);
           cs.setString(2,Nombre);
           cs.setDouble(3,Precio);
           cs.setInt(4,Cantidad);
           
          
           
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
           String sql="DELETE FROM productos WHERE Código=?";
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
   
   public ArrayList<Productos>BuscarNombre(String nombre){
       ArrayList<Productos>listaproducto=new ArrayList();
       Productos producto;
       
       try{
            Connection con= obtenerConexion();
             PreparedStatement cs=null;
            
           String sql="SELECT * FROM productos WHERE Nombre=?";
           cs=con.prepareStatement(sql);
           cs.setString(1, nombre);
           ResultSet rs=cs.executeQuery();
           while(rs.next()){
              producto =new Productos ();
               producto.setCodigo(rs.getString(1));
               producto.setNombre(rs.getString(2));
               producto.setPrecio(rs.getDouble(3));
               producto.setCantidad(rs.getInt(4));
               listaproducto.add(producto);
               
           }
           
            
       }
        catch(Exception e){
            
            
   }
       return listaproducto;
       
   }
}
