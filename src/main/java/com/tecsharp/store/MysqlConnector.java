package com.tecsharp.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * * @author evazquez
 */
/*******************************************************************************/
public class MysqlConnector {

    private static final String CONTROLADOR ="com.mysql.cj.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://192.168.1.76:3306/tecstore";
    //private static final String URL = "jdbc:mysql://remotemysql.com/w5v4lFjrUl";
    private static final String URL = "jdbc:mysql://localhost:3306/tecstore4";
    private static final String USUARIO = "root";
    private static final String CLAVE ="crowex45";   
    //REMOTEMYSQL
//    private static final String USUARIO = "w5v4lFjrUl";
//    private static final String CLAVE ="codBwH2hsq";   
//    private static final String USUARIO = "tecsharp";
//    private static final String CLAVE ="lWByHQH)SgM4Qd_K";
//    private static final String USUARIO = "tecsharp";
//    private static final String CLAVE ="crowex45";  -
    public int IdAutogenerado=0; //Se asigna el id del registro autogenerado por MySQL

    Connection conexion;
/*-----------------------------------------------------------------------------*/
/*                                 CONSTRUCTOR                                 */
    public MysqlConnector() {
      conexion = null;
      try {
            Class.forName(CONTROLADOR);
            conexion=DriverManager.getConnection(URL, USUARIO, CLAVE);
            if(conexion != null){
              //System.out.print("Conexión establecida... \n");
            }
      } catch  (ClassNotFoundException | SQLException e) {
              System.out.print("Error al conectar a la Base de Datos...");
      }
    }
/*-----------------------------------------------------------------------------*/
    public Connection getConnection(){
      return conexion;
    }      
/*-----------------------------------------------------------------------------*/
    public void desconectar(){
        try {
               if(conexion != null){
                   conexion.close();
                   conexion = null;
                   //System.out.println("Conexión terminada... ");   
               } 
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*-----------------------------------------------------------------------------*/
    public int ejecutarSentenciaSQL(String strSentenciaSQL){  
      try{
          PreparedStatement pstm=conexion.prepareStatement(strSentenciaSQL);
          pstm.execute();

          //SE CONSULTA EL ULTIMO ID AÑADIDO DE LA BD DEL CAMPO ID AUTOINCREMENTABLE
//          ResultSet rs=pstm.getGeneratedKeys();
//          if(rs.next()){
//             IdAutogenerado = rs.getInt(1);
//             System.out.println(rs.getInt(1));
//          }
//          boolean i = pstm.execute();
//          if(i){
//  	        System.out.println("Falló al agregar");
//  	      }
//  	      else{
//  	        System.out.println("Se agrego");
//  	      }
  	      
          
          return 1;
         } catch (SQLException e){
          System.out.println("Error al ejecutar Sentencia SQL..."+e);
          return 0;
         }
    }
/*-----------------------------------------------------------------------------*/    
    public ResultSet consultarRegistros(String strSentenciaSQL){       
      try {
           PreparedStatement pstm=conexion.prepareStatement(strSentenciaSQL);
           ResultSet respuesta=pstm.executeQuery();
           return respuesta;
        } catch (Exception e){
           System.out.println("Error al consultar los registros..."+e);
           return null;
        }
    }
/***********************************FIN DE LA CLASE*****************************/    
}
