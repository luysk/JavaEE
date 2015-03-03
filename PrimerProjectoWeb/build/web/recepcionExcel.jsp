<%-- 
    Document   : recepcionExcel
    Created on : 22/02/2015, 02:23:18 PM
    Author     : Batman
--%>

<%@page import="java.util.List"%>
<%@page import="java.io.FileInputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="BeanDatosExcel" scope="request" class="com.luis.bean.BeanDatosExcel" />
        <jsp:getProperty name="BeanDatosExcel" property="nombre" />
       
            <% List mi_archivo_excel = BeanDatosExcel.getMiExcel();
            for (int i = 0; i < mi_archivo_excel.size(); i++) {
			List celdas = (List) mi_archivo_excel.get(i);
			for (int j = 0; j < celdas.size(); j++) { %>
				<%= celdas.get(j) %>
			<%}%>
                        <br><br/>
		<%}
        %>
      
 </body>                                                                 
 </html>