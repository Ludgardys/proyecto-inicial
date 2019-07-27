/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CREUDMVC;
import Controlador.ctrlEmpleado;
import Controlador.ctrlProducto;
import Modelo.EmpleadosDAO;
import Modelo.ProductosDAO;
import Modelo.RegistrarEmp;
import Vista.Formulariofrm;
/**
 *
 * @author Ludgarys
 */
public class Distribuidora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Formulariofrm vistaC=new Formulariofrm();
        ProductosDAO modeloC=new ProductosDAO();
       ctrlProducto ctrl=new ctrlProducto(vistaC,modeloC);
        
        vistaC.setTitle("Registros");
        vistaC.setVisible(true);
        vistaC.setLocationRelativeTo(null);
        
        EmpleadosDAO modC=new EmpleadosDAO();
        RegistrarEmp mod=new RegistrarEmp ();
        Formulariofrm frm= new Formulariofrm();
        ctrlEmpleado ctrl2=new ctrlEmpleado(vistaC,modC);
        
    }
    
}
