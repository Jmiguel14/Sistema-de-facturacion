/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pablo
 */
public class ClsConexion {
      private Connection _base;
    private Statement _tabla;  
    private ResultSet _registros;
    
    private String nom;
    private String dir;
    private String desc;
    
    public ClsConexion (String dirbase){
        try{
        _base = DriverManager.getConnection("jdbc:ucanaccess://" + dirbase);
        _tabla = _base.createStatement(ResultSet.FETCH_UNKNOWN, ResultSet.TYPE_SCROLL_SENSITIVE);
        System.out.println("*** conexion exitosa ***");
        
        }catch (SQLException err){
              System.out.println("*** conexion errada ***" + err);
        }
    }

    public ResultSet registros() {
        return _registros;
    }
    
    public boolean consulta(String tabla) throws SQLException{
        boolean resp=false;
        String cadena= "SELECT * FROM " + tabla;
        _registros=null;
        _tabla.execute(cadena);
        _registros=_tabla.getResultSet();
        if(_registros!=null){
            resp=true;
        }
        return resp;
    }
        public void anterior()throws SQLException{
            try{
           if (!(_registros.previous())){
               _registros.last();
           }
       }catch(SQLException err){
             System.out.println("*** error recorrer ***" + err);
        }
    }
    public void siguiente()throws SQLException{
       try{
           if (!(_registros.next())){
               _registros.first();
           }
       }catch(SQLException err){
             System.out.println("*** error recorrer ***" + err);
        }        
    }
     public boolean insertarC(String tabla, String nom, String ape, String dir, String fecha, String telf, String email) throws SQLException{
        String values = "'"+nom+"', '"+ape+"', '"+dir+"', '"+fecha+"', '"+telf+"', '"+email+"'";
        String comando = "INSERT INTO " + tabla + "(nombre,apellido,direccion,fecha_nacimiento,telefono,email) VALUES("+values+")";
        int rowCount = _tabla.executeUpdate(comando);
       this.consulta("CLIENTE");
        return true;
    }
      public void actualizarC(String tabla, String nom, String ape, String dir, String fecha, String telf,  String email, int clave) throws SQLException{
      
        String comando ="UPDATE " + tabla + " SET "+"nombre='"+nom+"', apellido='"+ape+"', direccion='"+dir+"', fecha_nacimiento='"+fecha+"', telefono='"+telf+"', email='"+email+"' WHERE Id_Cliente='"+clave+"'";
        
         _tabla.executeUpdate(comando);
        this.consulta("CLIENTE");
        
    }
      public void eliminarC(String tabla, int clave) throws SQLException{
        String cadena= "DELETE FROM "+tabla+" WHERE Id_Cliente="+clave;
        _tabla.executeUpdate(cadena);
        this.consulta("CLIENTE");
    }  
    public boolean insertarP(String tabla, String nom, float precio, int stock ) throws SQLException{
        String values = "'"+nom+"', '"+precio+"', '"+stock+"'";
        String comando = "INSERT INTO " + tabla + "(nombre,precio,stock) VALUES("+values+")";
        int rowCount = _tabla.executeUpdate(comando);
       
        return true;
    }
      public void actualizarP(String tabla, String nom, float precio,int stock, int clave) throws SQLException{
      
        String comando ="UPDATE " + tabla + " SET "+"nombre='"+nom+"', precio='"+precio+"', stock='"+stock+"' WHERE Id_producto='"+clave+"'";
        
         _tabla.executeUpdate(comando);
        
        
    }
      public void eliminarP(String tabla, int clave) throws SQLException{
        String cadena= "DELETE FROM "+tabla+" WHERE Id_producto="+clave;
        _tabla.executeUpdate(cadena);
    } 
      public void consulta1(String Tabla) throws SQLException{
        String Cadena="SELECT * FROM"+Tabla;
        _tabla.execute(Cadena);
        _registros=_tabla.getResultSet();
        
        
    }
     
    
}
