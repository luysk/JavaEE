/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.luis.controllers;

import com.luis.basedatos.Base_Adapter;
import com.luis.basedatos.ConexionBase;
import com.luis.bean.BeanDatosExcel;
import com.luis.clases.Excel;
import com.luis.clases.Validacion_Excel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Batman
 */
@WebServlet(name = "SalidaController", urlPatterns = {"/SalidaController"})
public class SalidaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
           
        BeanDatosExcel datosExcel = new BeanDatosExcel();
        ConexionBase base = new ConexionBase();        
        Validacion_Excel val = new Validacion_Excel();
        Excel mi_clase_excel = new Excel();
        String ruta = mi_clase_excel.guardarArchivo(request);        
        if(!ruta.equals("-1")){
            
            List excel = mi_clase_excel.leerExcel(ruta); // falta verificar que no sea nulo
            
            List a = val.verificar_excel(excel);
            if(a !=null ){
                if(a.isEmpty()){  
                    Base_Adapter adapter = new Base_Adapter(base);
                    adapter.insertar(excel);
                    datosExcel.setNombre("Datos insertados");
                    datosExcel.setMiExcel(excel);                    
                    request.setAttribute("BeanDatosExcel", datosExcel);
                    request.getRequestDispatcher("recepcionExcel.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("salida.jsp").forward(request, response); //hay errores
                    System.err.println("Aqui 1");
                }
            }else{
                request.getRequestDispatcher("salida.jsp").forward(request, response);
                System.err.println("Aqui 2");
            }
        }else{
            System.out.println("Noooooooooooooooooooooooooooooooooooooooooooop");
            request.getRequestDispatcher("salida.jsp").forward(request, response);
            System.err.println("Aqui 3");
        }                
    }

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
