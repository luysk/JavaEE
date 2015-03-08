package com.luis.clases;

/**
 * Created by Batman on 19/02/2015.
 */


import java.io.File;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class Excel {
       
	public List leerExcel(String ruta){

            List archivo_excel = null;
            if(ruta.contains(".xlsx")){
                    archivo_excel = leerXLSX(ruta);
                    imprimirExcel(archivo_excel);
            }else{
                    if(ruta.contains(".xls")){
                            archivo_excel= leerXLS(ruta);
                            imprimirExcel(archivo_excel);
                    }else{
                            System.out.println("El archivo ingresado no tiene la extension necesaria para realizar el trabajo");
                    }
            }
            return archivo_excel;
	}

	private  List leerXLS(String ruta) {

		List mi_archivo_excel = new ArrayList();
		FileInputStream archivo = null;
		try {
			archivo = new FileInputStream(ruta);
			HSSFWorkbook libro_excel = new HSSFWorkbook(archivo);
			HSSFSheet[] hojas = new HSSFSheet[libro_excel.getNumberOfSheets()];
			for (int i = 0; i < hojas.length; i++) {
				hojas[i] = libro_excel.getSheetAt(i);
			}

			Iterator iterador_filas = hojas[0].rowIterator();
			while (iterador_filas.hasNext()) {
				HSSFRow fila_actual = (HSSFRow) iterador_filas.next();
				Iterator iterador_celdas = fila_actual.cellIterator();
				List lista_celdas = new ArrayList();
				while (iterador_celdas.hasNext()) {
					lista_celdas.add(getTipoCelda( (HSSFCell) iterador_celdas.next()));
				}
				mi_archivo_excel.add(lista_celdas);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mi_archivo_excel = null;
		} finally {
			if (archivo != null) {
				try {
					archivo.close();
				} catch (Exception e) {
					System.out.println("El archivo no se ha cerrado correctamente");
				}
			}
		}
		return mi_archivo_excel;
	}

	private  List leerXLSX(String ruta) {

		List mi_archivo_excel = null;
		FileInputStream archivo = null;
		try {
			archivo = new FileInputStream(ruta);
			XSSFWorkbook libro_excel = new XSSFWorkbook(archivo);
			XSSFSheet hojas[] = new XSSFSheet[libro_excel.getNumberOfSheets()];
			for(int i = 0;i<hojas.length;i++){
				hojas[i] = libro_excel.getSheetAt(i);
			}
			Iterator iterador_filas = hojas[0].rowIterator();

			mi_archivo_excel = new ArrayList();
			while (iterador_filas.hasNext()) {
				XSSFRow Fila_hssf = (XSSFRow) iterador_filas.next();
				Iterator iterador_celdas = Fila_hssf.cellIterator();
				List lista_celdas = new ArrayList();
				while (iterador_celdas.hasNext()) {
					lista_celdas.add(getTipoCelda((XSSFCell) iterador_celdas.next()));
				}
				mi_archivo_excel.add(lista_celdas);

			}

		} catch (Exception e) {
			e.printStackTrace();
			mi_archivo_excel = null;
		} finally {
			if(archivo != null){
				try {
					archivo.close();
				}catch (IOException e){
					e.printStackTrace();
					System.out.println("El archivo no se ha cerrado correctamente");
				}
			}
		}

		return mi_archivo_excel;
	}

	private static String getTipoCelda(HSSFCell celda) {

		String valor = "";
		switch (celda.getCellType()) {

			case 0:
				valor = "" + (int)celda.getNumericCellValue();
				break;
			case 1:
				valor = "" + celda.getStringCellValue();
				break;
			case 2:
				break;
			case 3:
				break;
		}
		return valor;
	}

	private  String getTipoCelda(XSSFCell celda) {

		String valor = "";
		switch (celda.getCellType()) {

			case 0:
				valor = "" + celda.getNumericCellValue();
				break;
			case 1:
				valor = "" + celda.getStringCellValue();
				break;
			case 2:
				break;
			case 3:
				break;
		}
		return valor;
	}

	private  void imprimirExcel(List mi_archivo_excel) {

		for (int i = 0; i < mi_archivo_excel.size(); i++) {
			List celdas = (List) mi_archivo_excel.get(i);
			for (int j = 0; j < celdas.size(); j++) {
				System.out.print(celdas.get(j) + " ");
			}
			System.out.println();
		}

	}
            
        public String guardarArchivo(HttpServletRequest req){
    
        String  ruta = "1";
         try{
            DiskFileUpload subirArchivo = new DiskFileUpload();             
            subirArchivo.setSizeMax(1024*1024); // 1 MB
            subirArchivo.setSizeThreshold(4096);
            subirArchivo.setRepositoryPath("C:\\temp");
            List listaItems =  subirArchivo.parseRequest(req);
            if(listaItems == null){
                ruta = "-1";
            }
	    Iterator i = listaItems.iterator();
            FileItem item_actual = null;
            while (i.hasNext()){
                item_actual = (FileItem)i.next();
                String fileName = item_actual.getName();
                if(fileName!=null){
                    File fichero = new File(fileName);
                    ruta = "C:\\Luka\\" + fichero.getName();
                    fichero = new  File(ruta);
                    item_actual.write(fichero);
                }
            }
        }catch(Exception e) {      
            e.printStackTrace();
            System.out.println();
            ruta = "-1";
        }    
        return ruta;
    }

}