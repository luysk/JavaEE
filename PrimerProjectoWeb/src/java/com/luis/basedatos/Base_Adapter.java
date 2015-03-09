/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luis.basedatos;

import java.util.List;

/**
 *
 * @author Batman
 */
public class Base_Adapter {
   
    
   ConexionBase base_datos = null; 
   public Base_Adapter(ConexionBase base){
       base_datos = base;
   }
   
   
   public void insertar(List excel){
   
       String   codigo_juego = "";
       String   codigo_consola = "";
       String   nombre = "";
         
       String sql = ""; 
       for(int i =0;i<excel.size();i++){
          List celdas = (List)excel.get(i);
          codigo_juego = celdas.get(0).toString();
          codigo_consola = celdas.get(1).toString();
          nombre = celdas.get(2).toString();
          sql = "insert into juego(codigo,consola,nombre) values("+codigo_juego+","
                                                            +codigo_consola+",'"+nombre+"');"; 
          System.err.println(sql);
          base_datos.insertarDatos(sql);
       }
       
   }
   
}
