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
public class Registro {
    public String usuario="ANALISIS";
    public String pass="analisis";
    public String puerto="1521";
    public String SID="XE";
    
    public String Nombre="";
    public String Correo="";
    public String Direccion="";
    public String Username="";
    public String Password="";
    public int Rol=0;
    
    public void setDatos(String nom, String co, String dir, String user, String pass, int r){
        this.Nombre=nom.trim();
        this.Correo=co.trim();
        this.Direccion= dir.trim();
        this.Username = user.trim();
        this.Password = pass.trim();
        this.Rol = r;
    }
    public void Agregar(){
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="insert into USUARIO(ID_USUARIO,NOMBRE,CORREO,DIRECCION,USERNAME,PASSWORD,ROL)VALUES(USUARIO_SEQ.nextval,'" + this.Nombre + "', '" + this.Correo + "', '" + this.Direccion + "', '" + this.Username + "', '" + this.Password + "', " + this.Rol + ")";
            Statement stmt = conn.createStatement();
            stmt.execute(consulta);
            stmt.close();
            conn.close();
        }catch(SQLException ex){
            
        }
    }
    public boolean Duplicados(String usuario){
        boolean retorno=false;
        try{    
            DriverManager.registerDriver (new  oracle.jdbc.driver.OracleDriver());
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:" + puerto + ":" + SID +  "", this.usuario, pass);
                // driver@machineName:port:SID           ,  userid,  password
            String consulta="select * from USUARIO where USERNAME='" + usuario.trim() + "'";
            //String consulta="select * from USUARIO where USERNAME='";
            Statement stmt = conn.createStatement();
            ResultSet rset =stmt.executeQuery(consulta);
            while (rset.next()){
                retorno=true;
            }
            rset.close();
            stmt.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return retorno;
    }
    
}
