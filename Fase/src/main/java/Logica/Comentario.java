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

/**
 *
 * @author mayaka
 */
public class Comentario {
    
    public static String usuario="analisis";
    public static String pass="analisis";
    public static String puerto="1521";
    public static String SID="XE";
    
    public String ID_COMENTARIO = "";
    public String DESCRIPCION = "";
    public String FECHA = "";
    public String USERNAME = "";
    public String ID_USUARIO = "";
    public String ID_PRODUCTO = "";
    
    public Comentario() {
    
    }

    public void AgregarComentarioABD(String comentario, String id_usuario, String id_producto) {
    
        this.AgregarComentario(comentario, id_usuario, id_producto);
        this.GrabarABD();
        
    }
    
    public boolean AgregarComentario(String comentario, String id_usuario, String id_producto) {
        if (comentario.equals("")==false && id_usuario.equals("")==false && id_producto.equals("")==false) {            
            
            this.DESCRIPCION = comentario;
            this.ID_USUARIO = id_usuario;
            this.ID_PRODUCTO = id_producto;
            this.FECHA = ObtenerFecha();
            
            return true;
        }
        
        return false;
    }          
    
    public boolean GrabarABD() {
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            
            String consulta="insert into COMENTARIO VALUES(COMENTARIO_SEQ.nextval, '" + 
                            DESCRIPCION + "', " + ObtenerFecha() + ", " + ID_USUARIO + ", " + 
                            ID_PRODUCTO + ")";
            
            Statement stmt = conn.createStatement();
            stmt.execute(consulta);
            stmt.close();
            conn.close();
            
            return true;
        }
        catch(SQLException ex){}
        
        return false;      
    }
    
    public static String ObtenerFecha(){
        // Ejemplo fecha="'09/09/2009'";
        
        String fecha;
        Calendar c1 = Calendar.getInstance();
        
        String anio = "" + c1.get(Calendar.YEAR);
        int mes =+ c1.get(Calendar.MONTH);
        String dia= "" + c1.get(Calendar.DAY_OF_MONTH);
        
        fecha = "'" + dia+"/" + (mes+1) + "/" + anio + "'";
        
        return fecha;
    }
    
    public static ArrayList<Comentario> ObtenerComentariosProducto(String producto) {
        ArrayList<Comentario> lista=new ArrayList<Comentario>();  
        int id_producto = Integer.parseInt(producto);
  
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            
            String consulta="select C.ID_COMENTARIO, C.DESCRIPCION, C.FECHA, C.USUARIO, U.USERNAME, C.PRODUCTO from COMENTARIO C"
                          + " INNER JOIN USUARIO U ON (C.USUARIO = U.ID_USUARIO)"
                          + " where C.PRODUCTO = " + id_producto + " order by C.FECHA desc";
            
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            
            Comentario temp;
            while (rset.next()){
                try{
                    temp = new Comentario();
                    temp.ID_COMENTARIO = rset.getObject(1).toString();
                    temp.DESCRIPCION = rset.getObject(2).toString();
                    temp.FECHA = rset.getObject(3).toString();
                    temp.ID_USUARIO = rset.getObject(4).toString();
                    temp.USERNAME = rset.getObject(5).toString();
                    temp.ID_PRODUCTO = rset.getObject(6).toString();
                    lista.add(temp);
                }
                catch(Exception e){}
                
            }
            stmt.close();
            rset.close();
            conn.close();
            
        }
        catch(SQLException ex){}        
        
        return lista;
    }
}
