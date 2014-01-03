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

/**
 *
 * @author Ariel-Vaio
 */
public class Usuario {
    private static String usuario="ANALISIS";
    private static String pass="analisis";
    private static String puerto="1521";
    private static String SID="XE";
    public int Id;
    public int Tipo;   
    public Usuario(){}
    public Usuario(int a, int b){
        Id = a;
        Tipo = b;
    }
    public boolean verificarUsername(String usern){
        boolean retorno = false;
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select * from Usuario where username = '"+usern+"'";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            while (rset.next()){
                retorno = true;
            }
            stmt.close();
            rset.close();
            conn.close();
        }catch(SQLException ex){
            
        }
        return retorno;
    }
    public boolean verificarPassword(String pas, String usern){
        boolean retorno = false;
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select id_usuario, rol from Usuario where password = '"+pas+"'  and username='"+usern+"'";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            while (rset.next()){
                Id=Integer.parseInt(rset.getObject(1).toString());
                Tipo=Integer.parseInt(rset.getObject(2).toString());
                retorno = true;
            }
            stmt.close();
            rset.close();
            conn.close();
        }catch(SQLException ex){
            
        }
        return retorno;
    }
}
