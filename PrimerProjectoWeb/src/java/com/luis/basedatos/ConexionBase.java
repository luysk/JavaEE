/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luis.basedatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Batman
 */
public class ConexionBase {

    private Connection conexion_base = null;    
    private int conexion_correcta = -1;
           
    public ConexionBase (){
        
        try{
            Class.forName("org.postgresql.Driver");
            System.err.println("Se hizo");
            try {
                String ubicacion_base = "jdbc:postgresql://localhost:5432/BaseJuegos";
                String constaseña = "password";
                conexion_base = DriverManager.getConnection(ubicacion_base,"postgres",constaseña);
                conexion_correcta = 1;
            } catch (SQLException e) {
                e.printStackTrace();
                conexion_correcta = -1;
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.err.println("No se ha encontrado la clase");
        }
        
    }
    
    public int getConexion_correcta() {
        return conexion_correcta;
    }
    
    public ResultSet EjecutarConsultaSQL (String sql){
        
        ResultSet resultado = null;
        try {            
            Statement sentencia = conexion_base.createStatement();
            resultado = sentencia.executeQuery(sql);            
        } catch (SQLException e) {
            System.out.println("No se ha ejecutado la consulta correctamente");
            resultado = null;
        }               
        return resultado;        
    }
    
    public void imprimirConsulta (ResultSet resultado_actual){
        
        try{            
            ResultSetMetaData metaData = resultado_actual.getMetaData();
            while(resultado_actual.next()){
                for(int i = 1; i <= metaData.getColumnCount();i++){
                    System.out.println(resultado_actual.getString(i));
                }
                System.out.println("");
            }
            resultado_actual.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public long insertarDatos(String sql){
        
        long resultado = -1;
        try {
            Statement sentencia = conexion_base.createStatement();
            resultado = sentencia.executeUpdate(sql);
            System.out.println("El resultado fue "+ resultado);
        } catch (SQLException e) {
            System.err.println("No se ha ejecutado correctamente");
            e.printStackTrace();
            resultado = -1;
        }
        return resultado;
    }
    
    public int  verificarConsola(String cod_consola){
        int exito = 0;
        ResultSet resultado = null;
        String sql = "select codigo from consola where codigo = '"+cod_consola+"';";
        try {
            Statement sentencia = conexion_base.createStatement();
            resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                exito = 1;
            }
        } catch (SQLException e) {
            exito = -1;
        }
        return exito;
    }  
    
    public int  verificarCodigoJuego(String cod_juego){
        int exito = 0;
        ResultSet resultado = null;
        String sql = "select codigo from juego where codigo = '"+cod_juego+"';";
        try {
            Statement sentencia = conexion_base.createStatement();
            resultado = sentencia.executeQuery(sql);
            ResultSetMetaData metadata = resultado.getMetaData();
            if(!resultado.next()){
                exito = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            exito = -1;
        }
        return exito;
    }
    
    
}
