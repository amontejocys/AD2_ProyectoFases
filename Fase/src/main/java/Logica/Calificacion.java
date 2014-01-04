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

/**
 *
 * @author Pablo David
 */
public class Calificacion {
    public static String usuario="analisis";
    public static String pass="analisis";
    public static String puerto="1521";
    public static String SID="XE";
    int id_usuario;
    public Calificacion(int id_usuario){
        this.id_usuario=id_usuario;
    }
    public ArrayList<Producto> ObtenerProductosNoCalificados(int id_usuario){
        ArrayList<Producto> retorno=new ArrayList<Producto>();
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select distinct producto.* from producto, factura, det_factura where ";
            consulta+="producto.id_producto=det_factura.PRODUCTO and det_factura.FACTURA=factura.ID_FACTURA and factura.USUARIO=" + id_usuario + "";
            consulta+=" and producto.ID_PRODUCTO not in ";
            consulta+="(select producto from calificacion where usuario=" + id_usuario + ");";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            if(rset.next()){
                try{
                    Producto temp=new Producto();
                    temp.ID_PRODUCTO=Integer.parseInt(rset.getObject(1).toString());
                    temp.NOMBRE=rset.getObject(2).toString();
                    temp.PRECIO=Double.parseDouble(rset.getObject(3).toString());
                    temp.CATEGORIA=Integer.parseInt(rset.getObject(4).toString());
                    temp.CANTIDAD=Integer.parseInt(rset.getObject(5).toString());
                    temp.CALIFICACION=Integer.parseInt(rset.getObject(6).toString());
                    temp.DESCRIPCION=rset.getObject(7).toString();
                    retorno.add(temp);
                }catch(Exception e){
                    
                }
            }
            stmt.close();
            rset.close();
            conn.close();
        }catch(SQLException ex){
            
        }
        return retorno;
    }
}
