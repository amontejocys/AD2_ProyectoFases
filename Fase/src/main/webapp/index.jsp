<%@page import ="Logica.*" %>
<%
    HttpSession session2 = request.getSession(true); 
    session2.setAttribute("UID", new Logica.Usuario(-1,0));
    Carrito car = new Carrito();
    session2.setAttribute("Carrito", car);
    response.sendRedirect("Catalogo.jsp?cat=1&exp=0");
%>