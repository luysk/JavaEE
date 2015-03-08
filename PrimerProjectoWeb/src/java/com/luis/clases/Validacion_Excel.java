/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luis.clases;

import com.luis.basedatos.ConexionBase;
import java.util.List;

/**
 *
 * @author Batman
 */
public class Validacion_Excel {
    
    ConexionBase base_datos;
    
    
    public Validacion_Excel() {
        
        base_datos = new ConexionBase();
    }
    
    public List verificar_excel (List archivo_excel){
    
        
        List filas_incorrectas = null;        
        if(archivo_excel != null){
            String sql = "";
            for (int i = 0; i < archivo_excel.size(); i++) {
                List celdas = (List) archivo_excel.get(i);
                int exito_codigo = base_datos.verificarCodigoJuego(celdas.get(i).toString());            
                int exito_cosola = base_datos.verificarConsola(celdas.get(i).toString());          
                int[] celdaIncorrecta = {-1,0,0};                
                if(exito_codigo != 1 ){
                    celdaIncorrecta[0] = i;
                    celdaIncorrecta[1] = -1;
                }                
                if (exito_cosola != 1){
                    celdaIncorrecta[0] = i;
                    celdaIncorrecta[2] = -1;
                }                            

                if(celdaIncorrecta[0] != -1){
                    filas_incorrectas.add(celdaIncorrecta);
                }
            }
        }
        return filas_incorrectas;
    }
    
    
    
}
