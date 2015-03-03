<%-- 
    Document   : salida
    Created on : 15/02/2015, 01:37:18 PM
    Author     : Batman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%= request.getParameter("nombre") %>
        
        <% int a= 0; 
        %>
        
        
        <%=a %>
        
        Tu nombre es ${nombre}
    </body>
</html>