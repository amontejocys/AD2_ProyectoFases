

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class Carrito {
    public static String usuario="analisis";
    public static String pass="analisis";
    public static String puerto="1521";
    public static String SID="XE";
    public ArrayList<Item> lista = new ArrayList<Item>();
    public boolean RealizarCompra(int id_usuario){
        boolean retorno=true;
        int id_factura=NuevoIDFactura()+1;
        if(id_factura!=0){
            {//creando factura
                try{    
                    DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
                    Connection conn = DriverManager.getConnection
                        ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                        // driver@machineName:port:SID           ,  userid,  password
                    String consulta="insert into FACTURA(ID_FACTURA,FECHA,USUARIO)VALUES(" + id_factura + ", " + ObtenerFecha() + ", " + id_usuario + ")";
                    Statement stmt = conn.createStatement();
                    stmt.execute(consulta);
                    stmt.close();
                    conn.close();
                }catch(SQLException ex){
                    retorno=false;
                }
            }
            {//creando los detalles
                Item temp;
                for (int i = 0; i < this.lista.size(); i++) {
                    temp=this.lista.get(i);
                    if(temp!=null){
                        try{    
                            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
                            Connection conn = DriverManager.getConnection
                                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                                // driver@machineName:port:SID           ,  userid,  password
                            String consulta="insert into DET_FACTURA(ID_DET_FACTURA,CANTIDAD,SUBTOTAL,FACTURA,PRODUCTO)VALUES(DET_FACTURA_SEQ.nextval, "+ temp.cantidad + "," + temp.subtotal+ ", " + id_factura + ", " + temp.id_producto+ ")";
                            Statement stmt = conn.createStatement();
                            stmt.execute(consulta);
                            stmt.close();
                            conn.close();
                        }catch(SQLException ex){
                            retorno=false;
                        }
                    }
                }
            }
        }else{
            retorno=false;
        }
        return retorno;
    }
    public String ObtenerFecha(){
        String fecha="'09/09/2009'";
        Calendar c1=Calendar.getInstance();
        String anio=""+c1.get(Calendar.YEAR);
        int mes=+c1.get(Calendar.MONTH);
        String dia=""+c1.get(Calendar.DAY_OF_MONTH);
        fecha="'"+dia+"/"+(mes+1)+"/"+anio+"'";
        return fecha;
    }
    public int NuevoIDFactura(){
        int id=0;
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select FACTURA_SEQ.nextval as ID from dual";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            if(rset.next()){
                try{
                    id=Integer.parseInt(rset.getObject("ID").toString());
                }catch(Exception e){
                    id=0;
                }
            }
            stmt.close();
            rset.close();
            conn.close();
        }catch(SQLException ex){
            id=0;
        }
        
        return id;
    }
    public void ReducirProducto(){
        Item temp;
        for (int i = 0; i < this.lista.size(); i++) {
            temp=this.lista.get(i);
            if(temp!=null){
                try{    
                    DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
                    Connection conn = DriverManager.getConnection
                        ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                        // driver@machineName:port:SID           ,  userid,  password
                    String consulta="update producto set cantidad=cantidad-" + temp.cantidad + " where id_producto=" + temp.id_producto;
                    Statement stmt = conn.createStatement();
                    stmt.execute(consulta);
                    stmt.close();
                    conn.close();
                }catch(SQLException ex){

                }
            }
        }
    }
    public boolean VerificarExistencia(int id_producto,int cantidad){
        boolean retorno=false;
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select * from producto where id_producto=" + id_producto + " and cantidad>=" + cantidad;
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            if (rset.next()){
                retorno=true;
            }
            stmt.close();
            rset.close();
            conn.close();
        }catch(SQLException ex){
            
        }
        return retorno;
    }
    public boolean AgregarProducto(String producto, int cantidad) {
        if (cantidad >= 100000)
            return false;
        else{
            Producto pa = new Producto();
            pa = Producto.DevolverProducto(producto);
            Item ya = BuscarProductoEnCarrito(producto);
            if(ya==null){
                Item it = new Item();
                it.id_producto=String.valueOf(pa.ID_PRODUCTO);
                it.cantidad=cantidad;
                it.subtotal=pa.PRECIO*it.cantidad;
                this.lista.add(it);
            }
            else{
                Item actualizado = SumarCantidad(ya, cantidad);
                if(actualizado!=null){
                    this.lista.remove(ya);
                    this.lista.add(actualizado);
                }
                else return false;
            }
            return true;
        }
    }
    
    public boolean VerificarExistencia(String id_producto, int cantidad) {
        
        int cantidad_bd = Producto.VerificarExistencia(id_producto, cantidad);        
        
        if (cantidad_bd!=-1) {  
            int cantidad_car = 0;
            for (int i=0; i<lista.size(); i++) {

                Item item = lista.get(i);
                if (item.id_producto.equals(id_producto)) {
                        
                   cantidad_car = item.cantidad;
                   break;
                }
            } 
            
            if (cantidad_bd >= cantidad + cantidad_car) {
                return true;
            }
        }
        
        return false;
    }
    
    public Item BuscarProductoEnCarrito(String id_producto){
        for(int i = 0; i < this.lista.size(); i++){
            if(this.lista.get(i).id_producto.equals(id_producto)){
                return this.lista.get(i);
            }
        }
        return null;
    }
    
    public Item SumarCantidad(Item it, int cantidadASumar){
        if (VerificarExistencia(it.id_producto, cantidadASumar)) {
            Item mod = new Item();
            mod = it;
            Double precio = mod.subtotal/mod.cantidad;
            mod.cantidad = it.cantidad+cantidadASumar;
            mod.subtotal = precio*mod.cantidad;
            return mod;
        }
        return null;
    }
    
    public boolean EliminarProducto(String producto) {
    
        for(int i=0; i<lista.size(); i++) {
            Item item = lista.get(i);
            
            if (item.id_producto.equals(producto)) {
                lista.remove(item);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean VaciarCarrito() {
        lista.clear();        
        return lista.isEmpty();
    }
    
}


