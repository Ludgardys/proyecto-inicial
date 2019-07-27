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
public class ctrlEmpleado implements ActionListener, KeyListener{
    Formulariofrm vistaFormulario=new Formulariofrm();
    EmpleadosDAO modeloFormulario=new EmpleadosDAO();
    
    
    public ctrlEmpleado (Formulariofrm vistaFormulario, EmpleadosDAO modeloFormulario){
        this.vistaFormulario= vistaFormulario;
        this.modeloFormulario=  modeloFormulario;
        
        this.vistaFormulario.btnInsertar1.addActionListener(this);
        this.vistaFormulario.btnActualizar1.addActionListener(this);
        this.vistaFormulario.btnEliminar1.addActionListener(this);
        this.vistaFormulario.btnEditar1.addActionListener(this);
        this.vistaFormulario.txtID.addActionListener(this);
        this.vistaFormulario.txtNombre1.addActionListener(this);
        this.vistaFormulario.txtApellido.addActionListener(this);
        this.vistaFormulario.txtDireccion.addActionListener(this);
        this.vistaFormulario.txtTelefono.addActionListener(this);
        this.vistaFormulario.btnOK1.addActionListener(this);
    }

    public ctrlEmpleado(RegistrarEmp mod, Formulariofrm frm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 public void LlenarTabla(JTable tablaE){                      //Modelo para la tabla
        DefaultTableModel modeloE= new DefaultTableModel();
        tablaE.setModel(modeloE);
        modeloE.addColumn("Cédula");
        modeloE.addColumn("Nombre");
        modeloE.addColumn("Apellido");
        modeloE.addColumn("Dirección");
        modeloE.addColumn("Teléfono");
        
        Object[]Columnas=new Object[5];
        int NumRegistros=modeloFormulario.listar().size();
        
        for(int i=0; i<NumRegistros;i++){
            Columnas[0]=modeloFormulario.listar().get(i).getCedula();
            Columnas[1]=modeloFormulario.listar().get(i).getNombre();
            Columnas[2]=modeloFormulario.listar().get(i).getApellido();
            Columnas[3]=modeloFormulario.listar().get(i).getDireccion();
            Columnas[4]=modeloFormulario.listar().get(i).getTelefono();
            
           modeloE.addRow(Columnas); 
            
        }
        
    }


 public void limpiar(){
     
     vistaFormulario.txtID.setText(null);
     vistaFormulario.txtID.setEditable(true);
     vistaFormulario.txtNombre1.setText(null);
     vistaFormulario.txtApellido.setText(null);
     vistaFormulario.txtDireccion.setText(null);
     vistaFormulario.txtTelefono.setText(null);
    }    
 
  public void limpiar1(){
     vistaFormulario.txtID.setText(null);
     vistaFormulario.txtID.setEditable(true);
     vistaFormulario.txtNombre1.setText(null);
     vistaFormulario.txtApellido.setText(null);
     vistaFormulario.txtDireccion.setText(null);
     vistaFormulario.txtTelefono.setText(null);
      
    } 

    
    
@Override
    public void actionPerformed (ActionEvent e){
        
        //INSERTAR
        if(e.getSource() == vistaFormulario.btnInsertar1){
            
            if(vistaFormulario.txtID.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Rellenar campo Cedula");
            }
            else if(vistaFormulario.txtNombre1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Rellenar campo Nombre");
            }
            else if(vistaFormulario.txtApellido.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Rellenar Campo Apellido");
            }
            else if(vistaFormulario.txtDireccion.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Rellenar campo Direccion");
            }
            
            String Cedula= vistaFormulario.txtID.getText().trim();
            String Nombre=vistaFormulario.txtNombre1.getText().trim().toLowerCase();
            String Apellido=vistaFormulario.txtApellido.getText().trim();
            String Direccion=vistaFormulario.txtDireccion.getText().trim();
            String Telefono=vistaFormulario.txtTelefono.getText().trim();
            
            String rptaRegistro = modeloFormulario.insertar(Cedula, Nombre, Apellido, Direccion, Telefono);
            if(rptaRegistro!=null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
                limpiar1();
              
            }else{
                JOptionPane.showMessageDialog(null,"Error al insertar");
               
            }
        }
        if (e.getSource() == vistaFormulario.btnActualizar1){
            LlenarTabla(vistaFormulario.jtDatos1);
           
        }
        
       
        
        //EDITAR
        if(e.getSource()==vistaFormulario.btnEditar1){
            int filaEditar=vistaFormulario.jtDatos1
                    .getSelectedRow();
            int numFs=vistaFormulario.jtDatos1.getSelectedRowCount();             //cantidad de filas seleccionadas
            
            if(filaEditar>=0&& numFs==1){
                vistaFormulario.txtID.setText(String.valueOf(vistaFormulario.jtDatos1.getValueAt(filaEditar,0)));
                vistaFormulario.txtID.setEditable(false);
                vistaFormulario.btnInsertar1.setEnabled(false);
                vistaFormulario.btnEditar1.setEnabled(false);
                vistaFormulario.btnEliminar1.setEnabled(false);
                vistaFormulario.txtBusqueda1.setEnabled(false);
                vistaFormulario.btnActualizar1.setEnabled(false);
                vistaFormulario.btnLimpiar1.setEnabled(false);
                   
            }else{
                JOptionPane.showMessageDialog(null, "Solo se permite seleccionar una fila");
                 limpiar1();
            }
        }
        
        
        
        //BOTON OK
        if(e.getSource()== vistaFormulario.btnOK1){           
                String Cedula=vistaFormulario.txtID.getText().trim();
                String Nombre=vistaFormulario.txtNombre1.getText().trim();
                String Apellido=vistaFormulario.txtApellido.getText().trim();
                String Direccion=vistaFormulario.txtDireccion.getText().trim();
                String Telefono=vistaFormulario.txtTelefono.getText().trim();
                
                
            
                int rptaEdit = modeloFormulario.EditarProducto(Cedula, Nombre, Apellido, Direccion, Telefono);
                if (rptaEdit>0){
                    JOptionPane.showMessageDialog(null,"Registro editado");
                     limpiar1();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al editarse");
                      
                }
                
                limpiar();
                vistaFormulario.btnInsertar1.setEnabled(true);
                vistaFormulario.btnEditar1.setEnabled(true);
                vistaFormulario.btnEliminar1.setEnabled(true);
                vistaFormulario.txtBusqueda1.setEnabled(true);
                vistaFormulario.btnOK1.setEnabled(false);
                vistaFormulario.btnActualizar1.setEnabled(true);
                vistaFormulario.btnLimpiar1.setEnabled(true);
        
                
            }
            
        
        
        //BOTON ELIMINAR
            if(e.getSource()==vistaFormulario.btnEliminar1){
                int filaInicio=vistaFormulario.jtDatos1.getSelectedRow();
                int numFS=vistaFormulario.jtDatos1.getSelectedRowCount();
                ArrayList<String>listaCedula=new ArrayList();
                String cedula="";
                if(filaInicio>0){
                    for(int i=0;i<numFS;i++){
                    cedula=String.valueOf(vistaFormulario.jtDatos1.getValueAt(i+filaInicio, 0));
                    listaCedula.add(cedula);
                    }
                    for(int i=0;i<numFS;i++){
                    int rptaUsuario=JOptionPane.showConfirmDialog(null, "Desea eliminar el registro con"+cedula+"?");
                    
                    if(rptaUsuario==0){
                        
                            modeloFormulario.EliminarProducto(cedula);
                      
                        }
                    }
                
                    LlenarTabla(vistaFormulario.jtDatos1);
            }else{
                  JOptionPane.showMessageDialog(null,"Seleccione una fila para eliminar");
                  limpiar1();
                }
            }
            
            if(e.getSource()==vistaFormulario.btnLimpiar1){
            limpiar1();
        }
          
            
    }
        
    

    @Override
    public void keyTyped(KeyEvent e) {
    
      if(e.getSource()==vistaFormulario.txtID||e.getSource()==vistaFormulario.txtNombre1||e.getSource()==vistaFormulario.txtApellido||e.getSource()==vistaFormulario.txtDireccion||
         e.getSource()==vistaFormulario.txtTelefono){
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
       if(e.getSource()==vistaFormulario.txtBusqueda1){
        String nombre=vistaFormulario.txtBusqueda1.getText();
        DefaultTableModel modeloE= new DefaultTableModel();
        vistaFormulario.jtDatos.setModel(modeloE);
        modeloE.addColumn("Cedula");
        modeloE.addColumn("Nombre");
        modeloE.addColumn("Apellido");
        modeloE.addColumn("Dirección");
        modeloE.addColumn("Teléfono");
        
        
        Object[]Columnas=new Object[5];
        int NumRegistros=modeloFormulario.BuscarNombre(nombre).size();
        
        for(int i=0; i<NumRegistros;i++){
            Columnas[0]=modeloFormulario.BuscarNombre(nombre).get(i).getCedula();
            Columnas[1]=modeloFormulario.BuscarNombre(nombre).get(i).getNombre();
            Columnas[2]=modeloFormulario.BuscarNombre(nombre).get(i).getApellido();
            Columnas[3]=modeloFormulario.BuscarNombre(nombre).get(i).getDireccion();
            Columnas[4]=modeloFormulario.BuscarNombre(nombre).get(i).getTelefono();
           modeloE.addRow(Columnas); 
       }
    }
       
    }  
}
