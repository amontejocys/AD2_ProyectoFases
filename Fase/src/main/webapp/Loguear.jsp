<%-- 
    Document   : Loguear
    Created on : 19/09/2013, 02:36:15 AM
    Author     : Ariel-Vaio
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%
    HttpSession session2 = request.getSession(true);
    Logica.Usuario a = (Logica.Usuario)session2.getAttribute("UID");
    if (a.verificarUsername(request.getParameter("Username"))){
        if(a.verificarPassword(request.getParameter("Pass"),request.getParameter("Username"))){
            session2.setAttribute("Error", "0");
            session2.setAttribute("UID",a);
            response.sendRedirect("index2.jsp");
        }
        else{
            session2.setAttribute("Error", "-2");
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    else{session2.setAttribute("Error", "-1");
        response.sendRedirect(request.getHeader("Referer"));}
%>
