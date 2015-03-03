/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luis.bean;

/**
 *
 * @author Batman
 */


public class BeanDatosIngreso {
    
    String nombre;
    String constrasena;
    
    public void setNombre(String nombre_entrante){
        nombre = nombre_entrante;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setCotrasena(String contrasena_entrante){
        constrasena = contrasena_entrante;
    }
    
    public String getContrasena(){
        return constrasena;
    }

    
   
}
