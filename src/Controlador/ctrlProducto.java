/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.*;
import Vista.Formulariofrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Ludgarys
 */
public class ctrlProducto implements ActionListener,KeyListener {
      Formulariofrm vistaFormulario=new Formulariofrm();
    ProductosDAO modeloFormulario=new ProductosDAO();
    
    public ctrlProducto (Formulariofrm vistaFormulario, ProductosDAO modeloFormulario){
        this.vistaFormulario= vistaFormulario;
        this.modeloFormulario=  modeloFormulario;
        
        this.vistaFormulario.btnInsertar.addActionListener(this);
        this.vistaFormulario.btnActualizar.addActionListener(this);
        this.vistaFormulario.btnActualizar1.addActionListener(this);
        this.vistaFormulario.btnEliminar.addActionListener(this);
        this.vistaFormulario.btnEditar.addActionListener(this);
        this.vistaFormulario.txtCodigo.addActionListener(this);
        this.vistaFormulario.txtNombre.addActionListener(this);
        this.vistaFormulario.txtPrecio.addActionListener(this);
        this.vistaFormulario.txtCantidad.addActionListener(this);
        this.vistaFormulario.btnOK.addActionListener(this);
    }
    

    public void InicializarCRUD(){
        
    }
    
    public void LlenarTabla(JTable tablaD){                      //Modelo para la tabla
        DefaultTableModel modeloT= new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("Codigo");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Precio");
        modeloT.addColumn("Cantidad");
        
        Object[]Columna=new Object[4];
        int NumRegistros=modeloFormulario.listar().size();
        
        for(int i=0; i<NumRegistros;i++){
            Columna[0]=modeloFormulario.listar().get(i).getCodigo();
            Columna[1]=modeloFormulario.listar().get(i).getNombre();
            Columna[2]=modeloFormulario.listar().get(i).getPrecio();
            Columna[3]=modeloFormulario.listar().get(i).getCantidad();
           modeloT.addRow(Columna); 
            
        }
        
    }


 public void limpiar(){
     
     vistaFormulario.txtCodigo.setText(null);
     vistaFormulario.txtCodigo.setEditable(true);
     vistaFormulario.txtNombre.setText(null);
     vistaFormulario.txtPrecio.setText(null);
     vistaFormulario.txtCantidad.setText(null);
      
    }    
    

    
    
@Override
    public void actionPerformed (ActionEvent e){
        
        //INSERTAR
        if(e.getSource()==vistaFormulario.btnInsertar){
            String Codigo=vistaFormulario.txtCodigo.getText().trim();
            String Nombre=vistaFormulario.txtNombre.getText().trim().toLowerCase();
            Double Precio=Double.parseDouble(vistaFormulario.txtPrecio.getText().trim());
            int Cantidad=Integer.parseInt(vistaFormulario.txtCantidad.getText().trim());
            
            String rptaRegistro=modeloFormulario.insertar(Codigo, Nombre, Precio, Cantidad);
            if(rptaRegistro!=null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
              
            }else{
                JOptionPane.showMessageDialog(null,"Error al insertar");
                
            }
        }
        if (e.getSource()==vistaFormulario.btnActualizar){
            LlenarTabla(vistaFormulario.jtDatos);
           
        }
        
       
        
        //EDITAR
        if(e.getSource()==vistaFormulario.btnEditar){
            int filaEditar=vistaFormulario.jtDatos.getSelectedRow();
            int numFs=vistaFormulario.jtDatos.getSelectedRowCount();             //cantidad de filas seleccionadas
            
            if(filaEditar>=0&& numFs==1){
                vistaFormulario.txtCodigo.setText(String.valueOf(vistaFormulario.jtDatos.getValueAt(filaEditar,0)));
                vistaFormulario.txtCodigo.setEditable(false);
                vistaFormulario.btnInsertar.setEnabled(false);
                vistaFormulario.btnEditar.setEnabled(false);
                vistaFormulario.btnEliminar.setEnabled(false);
                vistaFormulario.txtBusqueda.setEnabled(false);
                vistaFormulario.btnActualizar.setEnabled(false);
                vistaFormulario.btnLimpiar.setEnabled(false);
                  
            }else{
                JOptionPane.showMessageDialog(null, "Solo se permite seleccionar una fila");
              
            }
        }
        
        
        
        //BOTON OK
        if(e.getSource()== vistaFormulario.btnOK){           
                String Codigo=vistaFormulario.txtCodigo.getText().trim();
                String Nombre=vistaFormulario.txtNombre.getText().trim();
                Double Precio=Double.parseDouble(vistaFormulario.txtPrecio.getText().trim());
                int Cantidad=Integer.parseInt(vistaFormulario.txtCantidad.getText().trim());
                
                
            
                int rptaEdit = modeloFormulario.EditarProducto(Codigo, Nombre, Precio, Cantidad);
          
                if (rptaEdit>0){
                    JOptionPane.showMessageDialog(null,"Registro editado");
                    
                }else{
                    JOptionPane.showMessageDialog(null,"Error al editarse");
                  
                }
                
                limpiar();
                vistaFormulario.btnInsertar.setEnabled(true);
                vistaFormulario.btnEditar.setEnabled(true);
                vistaFormulario.btnEliminar.setEnabled(true);
                vistaFormulario.txtBusqueda.setEnabled(true);
                vistaFormulario.btnOK.setEnabled(false);
                vistaFormulario.btnActualizar.setEnabled(true);
        
                
            }
            
        
        
        //BOTON ELIMINAR
            if(e.getSource()==vistaFormulario.btnEliminar){
                int filaInicio=vistaFormulario.jtDatos.getSelectedRow();
                int numFS=vistaFormulario.jtDatos.getSelectedRowCount();
                ArrayList<String>listaCodigo=new ArrayList();
                String codigo="";
                if(filaInicio>0){
                    for(int i=0;i<numFS;i++){
                    codigo=String.valueOf(vistaFormulario.jtDatos.getValueAt(i+filaInicio, 0));
                    listaCodigo.add(codigo);
                    }
                    for(int i=0;i<numFS;i++){
                    int rptaUsuario=JOptionPane.showConfirmDialog(null, "Desea eliminar el registro con"+codigo+"?");
                    
                    if(rptaUsuario==0){
                        
                            modeloFormulario.EliminarProducto(codigo);
                      
                        }
                    }
                
                    LlenarTabla(vistaFormulario.jtDatos);
            }else{
                  JOptionPane.showMessageDialog(null,"Seleccione una fila para eliminar");
                 
                }
            }
            
            if(e.getSource()==vistaFormulario.btnLimpiar){
            limpiar();
        }
          
            
    }
        
    

    @Override
    public void keyTyped(KeyEvent e) {
      if(e.getSource()==vistaFormulario.txtCodigo||e.getSource()==vistaFormulario.txtCantidad){
          char c=e.getKeyChar();
          if(c<'0'||c>'9'){
              e.consume();
          }
      }
      if(e.getSource()==vistaFormulario.txtNombre){
          char c=e.getKeyChar();
          if((c<'a'||c>'z')&&(c<'A'||c>'Z')){
              e.consume();
          }
      }
          
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if(e.getSource()==vistaFormulario.txtBusqueda){
           String nombre=vistaFormulario.txtBusqueda.getText();
        DefaultTableModel modeloT= new DefaultTableModel();
        vistaFormulario.jtDatos.setModel(modeloT);
        modeloT.addColumn("Codigo");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Precio");
        modeloT.addColumn("Cantidad");
        
        Object[]Columna=new Object[4];
        int NumRegistros=modeloFormulario.BuscarNombre(nombre).size();
        
        for(int i=0; i<NumRegistros;i++){
            Columna[0]=modeloFormulario.BuscarNombre(nombre).get(i).getCodigo();
            Columna[1]=modeloFormulario.BuscarNombre(nombre).get(i).getNombre();
            Columna[2]=modeloFormulario.BuscarNombre(nombre).get(i).getPrecio();
            Columna[3]=modeloFormulario.BuscarNombre(nombre).get(i).getCantidad();
           modeloT.addRow(Columna); 
       }
    }
       

    
}   
    
}
