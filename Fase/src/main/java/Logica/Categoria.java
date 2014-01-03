/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ARIAS
 */
public class Categoria {
    public static String usuario="ANALISIS";
    public static String pass="analisis";
    public static String puerto="1521";
    public static String SID="XE";
    
    public int ID_CATEGORIA;
    public String NOMBRE;
    
    public static ArrayList<Categoria> ObtenerCategorias(){
        ArrayList<Categoria> retorno=new ArrayList<Categoria>();
        Categoria cat = null;
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select * from CATEGORIA order by id_categoria";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            while (rset.next()){
                try{
                    cat = new Categoria();
                    cat.ID_CATEGORIA=Integer.parseInt(rset.getObject(1).toString());
                    cat.NOMBRE=rset.getObject(2).toString();
                    retorno.add(cat);
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
    
    public static ArrayList<Producto> ObtenerProductos(String id_categorias){
        int id_categoria = Integer.parseInt(id_categorias);
        Producto prod=null;
        ArrayList<Producto> retorno=new ArrayList<Producto>();
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select * from PRODUCTO where CATEGORIA ="+id_categoria + " order by id_producto";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            while (rset.next()){
                try{
                    prod = new Producto();
                    prod.ID_PRODUCTO=Integer.parseInt(rset.getObject(1).toString());
                    prod.NOMBRE=rset.getObject(2).toString();
                    prod.PRECIO=Double.parseDouble(rset.getObject(3).toString());
                    prod.CATEGORIA=Integer.parseInt(rset.getObject(4).toString());
                    prod.CANTIDAD=Integer.parseInt(rset.getObject(5).toString());
                    prod.CALIFICACION=Integer.parseInt(rset.getObject(6).toString());
                    prod.DESCRIPCION=rset.getObject(7).toString();
                    retorno.add(prod);
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
    public static String AgregarNuevaCategoria(String nombre){
            try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select * from CATEGORIA where nombre ='"+nombre+"'";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            boolean repetido = false;
            while (rset.next()){
                repetido = true;
            }
            stmt.close();
            if(!repetido){
            String consulta2="insert into CATEGORIA VALUES(CATEGORIA_SEQ.nextval, ?)";
            PreparedStatement stmt2 = conn.prepareStatement(consulta2);
            stmt2.setString(1, nombre);
            stmt2.executeUpdate();
            stmt2.close();
            conn.close();
            return "";
            }
            else{
            conn.close();
            return "Categoria ya existe";
            }
        }
        catch(SQLException ex){
            return "Imposible agregar categoria";
        }
    }
}
