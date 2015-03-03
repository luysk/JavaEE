/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luis.bean;

import java.util.List;

/**
 *
 * @author Batman
 */
public class BeanDatosExcel {
    
    private List mi_archivo_excel = null;
    private String nombre;
    
    public void setMiExcel(List excel){
        mi_archivo_excel = excel;
    }
    
    public List getMiExcel(){
        return mi_archivo_excel;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String name){
        nombre = name;
    }

}
