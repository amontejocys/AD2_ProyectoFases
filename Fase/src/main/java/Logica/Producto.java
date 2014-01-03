/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo David
 */
public class Producto {
    public static String usuario="analisis";
    public static String pass="analisis";
    public static String puerto="1521";
    public static String SID="XE";
    
    public int ID_PRODUCTO;
    public String NOMBRE;
    public Double PRECIO;
    public int CATEGORIA;
    public int CANTIDAD;
    public int CALIFICACION;
    public String DESCRIPCION;
    public static Producto DevolverProducto(String id_productos){
        int id_producto = Integer.parseInt(id_productos);
        Producto retorno=null;
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select * from PRODUCTO where ID_PRODUCTO=" + id_producto + " and rownum=1";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            while (rset.next()){
                try{
                    retorno=new Producto();
                    retorno.ID_PRODUCTO=Integer.parseInt(rset.getObject(1).toString());
                    retorno.NOMBRE=rset.getObject(2).toString();
                    retorno.PRECIO=Double.parseDouble(rset.getObject(3).toString());
                    retorno.CATEGORIA=Integer.parseInt(rset.getObject(4).toString());
                    retorno.CANTIDAD=Integer.parseInt(rset.getObject(5).toString());
                    retorno.CALIFICACION=Integer.parseInt(rset.getObject(6).toString());
                    retorno.DESCRIPCION=rset.getObject(7).toString();
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
    public int ObtenerNoColumnas(String consulta) throws SQLException {
        DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection
            ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
            // driver@machineName:port:SID           ,  userid,  password

        Statement stmt = conn.createStatement();
        ResultSet rset = 
        stmt.executeQuery(consulta);
        int contador=1;
        while (rset.next()){
            try{
                while(rset.getObject(contador)!=null){
                    contador++;
                }
            }catch(Exception e){
                
            }
            break;
        }
        stmt.close();
        rset.close();
        conn.close();
        return (contador-1);
    }
    public static int AgregarSugerencia(String id_usuarios,String id_productos){
        int id_usuario = Integer.parseInt(id_usuarios);
        int id_producto = Integer.parseInt(id_productos);
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String sql = "update SUGERENCIA set RATING = RATING + 1  where ID_USUARIO = ? and ID_PRODUCTO = ?" ;            
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id_usuario);
            pstmt.setInt(2, id_producto);

            int res = pstmt.executeUpdate();
            pstmt.close();
            
            if (res==0) {
                sql = "insert into SUGERENCIA values (SUGERENCIA_SEQ.nextval, ?, ?, 1)" ;
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, id_usuario);
                pstmt.setInt(2, id_producto);

                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
                
                return 2;
            }         
            conn.close();
            
            return 1;

        }catch(SQLException ex){
     
        }
        return -1;
    }
    public static ArrayList<Producto> DevolverSugerencias(String id_usuarios){
        int id_usuario = Integer.parseInt(id_usuarios);
        ArrayList<Producto> retorno=new ArrayList<Producto>();
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select prod.* from PRODUCTO prod,SUGERENCIA sug where sug.ID_USUARIO=" + id_usuario + " and prod.ID_PRODUCTO=sug.ID_PRODUCTO and rownum<11 order by sug.RATING desc";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            Producto temp;
            while (rset.next()){
                try{
                    temp=new Producto();
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
    
    public static int VerificarExistencia(String producto, int cantidad) {
        int id_producto = Integer.parseInt(producto);
         
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            
            String consulta="select id_producto, cantidad from PRODUCTO where id_producto = " + id_producto + " "
                          + "and cantidad >= " + cantidad;
            
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            
            if (rset.next()){                
                //Retorna la cantidad [servira luego]
                return Integer.parseInt(rset.getObject(2).toString());
            }            
            stmt.close();
            rset.close();
            conn.close();
        }
        catch(SQLException ex){}
        
        return -1;    
    }
    public static boolean AgregarNuevoProducto(String nombre, Double precio, int categoria, int cantidad, String descripcion){
        if(precio > 0.0 && categoria > 0 && cantidad > 0){
            try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            
            String consulta="insert into PRODUCTO VALUES(PRODUCTO_SEQ.nextval, ?,?,?,?,?,?)";
            
            PreparedStatement stmt = conn.prepareStatement(consulta);
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, categoria);
            stmt.setInt(4, cantidad);
            stmt.setInt(5, 4);
            stmt.setString(6, descripcion);
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            
            return true;
        }
        catch(SQLException ex){
            return false;
        }
        }
        else
        return false;
    }
}
