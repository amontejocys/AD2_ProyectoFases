<%-- 
    Document   : Registro
    Created on : 8/09/2013, 01:18:52 PM
    Author     : Ariel-Vaio
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<% 
    HttpSession session2 = request.getSession(true);
    if(session2.getAttribute("Error")==null){
        session2.setAttribute("Error", "false");
    }
    if(session2.getAttribute("Reg")==null){
        session2.setAttribute("Reg", new Logica.Registro());
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Station Shop Theme - Comprar Page</title>
<meta name="keywords" content="station shop, checkout, theme, free templates, website templates, CSS, HTML" />
<meta name="description" content="Station Shop Theme, Comprar, free CSS template provided by templatemo.com" />
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js">

/***********************************************
* Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<script language="javascript" type="text/javascript">
function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;
}
</script>

<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "top_nav", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script>

<link rel="stylesheet" type="text/css" media="all" href="css/jquery.dualSlider.0.2.css" />

<script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/jquery.timers-1.2.js" type="text/javascript"></script>

</head>

<body>

<div id="templatemo_wrapper">
	<div id="templatemo_header">
    	
    	<div id="site_title">
        	<h1><a href="http://www.templatemo.com">Free CSS Templates</a></h1>
        </div>
        
        <div id="header_right">
	         <a href="#">Registro</a></a>
		</div>
        
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_header -->
    
    <div id="templatemo_menu">
    	<div id="top_nav" class="ddsmoothmenu">
            <ul>
                <li><a href="index.html">Home</a></li>
                <li><a href="">Catalogo</a>
                    <ul>
                        <li><a href="http://www.templatemo.com/page/1">Sub menu 1</a></li>
                        <li><a href="http://www.templatemo.com/page/2">Sub menu 2</a></li>
                        <li><a href="http://www.templatemo.com/page/3">Sub menu 3</a></li>
                  	</ul>
                </li>
                <li><a href="Agregar.jsp">Agregar</a>
                    <ul>
                       <li><a href="http://www.templatemo.com/page/1">Categoria</a></li>
                        <li><a href="http://www.templatemo.com/page/2">Producto</a></li>
                  	</ul>
                </li>
                <li><a href="faqs.html">FAQs</a></li>
                <li><a href="Compra.jsp" >Comprar</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
            <br style="clear: left" />
        </div> <!-- end of ddsmoothmenu -->
        <div id="menu_second_bar">
        	<div id="top_shopping_cart">
            	Shopping Cart: <strong>3 Products</strong> ( <a href="#">Show Cart</a> )
            </div>
        	<div id="templatemo_search">
                <form action="#" method="get">
                  <input type="text" value="Search" name="keyword" id="keyword" title="keyword" onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                  <input type="submit" name="Search" value=" Search " alt="Search" id="searchbutton" title="Search" class="sub_btn"  />
                </form>
            </div>
            <div class="cleaner"></div>
    	</div>
    </div> <!-- END of templatemo_menu -->
    
    <div id="templatemo_main">
   		<div id="sidebar" class="float_l">
        	<div class="sidebar_box"><span class="bottom"></span>
            	<h3>Categorias<a href="http://es.forwallpaper.com" title="visit es.forwallpaper.com" class="more_link"  target="_blank"></a></h3>   
                <div class="content"> 
                	<ul class="sidebar_list">
                    	    <%@page import ="Logica.*" %>
                        <%
                        Logica.Categoria cat = new Logica.Categoria();
                        java.util.ArrayList<Categoria> listaCategorias = new java.util.ArrayList<Categoria>();
                        listaCategorias = cat.ObtenerCategorias();
                        int largo = listaCategorias.size();
                        for(int i = 0; i < largo; i++){
                        %>
                        <li><a href="Catalogo.jsp?cat=<%=listaCategorias.get(i).ID_CATEGORIA%>&exp=0"><%=listaCategorias.get(i).NOMBRE%></a></li>
                        <%
                        }
                        %>
                    </ul>
                </div>
            </div>
            <div class="sidebar_box"><span class="bottom"></span>
            	<h3>Sugerencias <a href="http://es.forwallpaper.com" title="visit es.forwallpaper.com" class="more_link"  target="_blank"></a></h3>   
                        <%if(((Logica.Usuario)session2.getAttribute("UID")).Id!=-1){%>
                <div class="content"> 
                       <%
                        Logica.Producto pe = new Logica.Producto();
                        java.util.ArrayList<Producto> listaSug = new java.util.ArrayList<Producto>();
                        Logica.Usuario a = (Logica.Usuario)session2.getAttribute("UID");
                        listaSug = pe.DevolverSugerencias(a.Id+"");
                        int largosd = listaSug.size();
                        for(int i = 0; i < largosd; i++){
                        Producto temp = listaSug.get(i);
                        %>
                        
                	<div class="bs_box">
                    	<a href="#"><img src="images/product/<%=temp.NOMBRE%>.jpg" alt="Image 01" /></a>
                        <h4><a href="Producto.jsp?id=<%=temp.ID_PRODUCTO%>"><%=temp.NOMBRE%></a></h4>
                        <p class="price"><%=temp.PRECIO%></p>
                        <div class="cleaner"></div>
                         </div>
                        <%
                        }
                        %>
                
                 
                </div>
                <%}%>
            </div>
        </div>
        <div id="content" class="float_r">
        	<h1>Iniciar Sesion</h1>
        
            <h5>Informacion de Usuario</h5>
            <form id="campos" action ="Loguear.jsp" method="post">
            <div class="content_half float_l checkout">
                <%
                    if(session2.getAttribute("Error").toString().equals("-1")){
                        %>
                        <p style="color:red">No existe el nombre de usuario</p> <br />
                        <%
                    }
                    if(session2.getAttribute("Error").toString().equals("-2")){
                        %>
                        <p style="color:red">La contraseña no coincide con el username</p> <br />
                        <%
                    }
                 %>
            	Username:
				<input type="text"  style="width:300px;"  name="Username"/>
                 
                Contraseña:<br />
				
                <input type="password"  style="width:300px;"  name="Pass" />
                
               <br />
				
                <input type="submit" value="Entrar"    />
	
            </div>
            
            </form>
            <div class="cleaner h50"></div>
		</div>
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_main -->
    
    <div id="templatemo_footer">
    	<p>
			<a href="index.html">Home</a> | <a href="">Catalogo</a> | <a href="Agregar.jsp">Agregar</a> | <a href="faqs.html">FAQs</a> | <a href="Compra.jsp">Comprar</a> | <a href="contact.html">Contact</a>
		</p>

    	Copyright © 2048 <a href="#">Your Company Name</a> | Designed by <a href="http://www.templatemo.com" target="_parent">Free CSS Templates</a>
    </div> <!-- END of templatemo_footer -->
    
</div> <!-- END of templatemo_wrapper -->


<script type='text/javascript' src='js/logging.js'></script>
</body>
