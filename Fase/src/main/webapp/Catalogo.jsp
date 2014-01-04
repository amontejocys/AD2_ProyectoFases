
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Tienda Virtual - Catalogo</title>
<meta name="keywords" content="station shop, theme, free template, templatemo, CSS, HTML" />
<meta name="description" content="Station Shop Theme, free CSS template provided by templatemo.com" />
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
<% 
    HttpSession session2 = request.getSession(true);
    session2.setAttribute("SUG", "1");
    //session2.setAttribute("UID", "1");
%>
<script language="javascript" type="text/javascript">
function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;
}
function Sugerencia(donde)
{
   
    window.locationf="Producto.jsp?id="+donde;
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
<script src="js/jquery.dualSlider.0.3.min.js" type="text/javascript"></script>

<script type="text/javascript">
    
    $(document).ready(function() {
        
        $(".carousel").dualSlider({
            auto:true,
            autoDelay: 6000,
            easingCarousel: "swing",
            easingDetails: "easeOutBack",
            durationCarousel: 1000,
            durationDetails: 600
        });
        
    });
    
</script>

</head>

<body>

<div id="templatemo_wrapper">
	<div id="templatemo_header">
    
    	<div id="site_title">
        	<h1><a href="http://www.templatemo.com">Tienda Virtual</a></h1>
        </div>
        
        <div id="header_right">
            <%if(((Logica.Usuario)session2.getAttribute("UID")).Id!=-1){%>
	        <a href="#">Mi Cuenta</a>  | <a href="#">Mi Carrito</a> | <a href="Compra.jsp">Comprar</a> | <a href="index.jsp">Salir </a>|<%}%> <a href="Logueo.jsp">Log In</a> |<a href="Registro.jsp">Registro </a> 
		</div>
        
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_header -->
    
    <div id="templatemo_menu">
    	<div id="top_nav" class="ddsmoothmenu">
            <ul>
                <li><a href="index.html" class="selected">Home</a></li>
                <li><a href="">Catalogo</a>
                    <ul>
                        <li><a href="http://www.templatemo.com/page/1">Sub menu 1</a></li>
                        <li><a href="http://www.templatemo.com/page/2">Sub menu 2</a></li>
                        <li><a href="http://www.templatemo.com/page/3">Sub menu 3</a></li>
                  </ul>
                </li>
                <li><a href="Agregar.jsp">Agregar</a>
                    <ul>
                        <li><a href="AgregarCat.jsp">Categoria</a></li>
                        <li><a href="Agregar.jsp">Producto</a></li>
                  </ul>
                </li>
                <li><a href="faqs.html">FAQs</a></li>
                <li><a href="Compra.jsp">Comprar</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
            <br style="clear: left" />
        </div> <!-- end of ddsmoothmenu -->
        <div id="menu_second_bar">
        	<div id="top_shopping_cart">
            	                  <%@page import ="Logica.*" %>
                   <%if(((Logica.Usuario)session2.getAttribute("UID")).Id!=-1){%>
            	Shopping Cart: <strong><%=((java.util.ArrayList<Item>)(((Carrito)session2.getAttribute("Carrito")).lista)).size()%> Products</strong> ( <a href="Carrito.jsp">Show Cart</a> )
                <%}%>

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
    
    <div id="templatemo_middle" class="carousel">
    	<div class="panel">
				
				<div class="details_wrapper">
					
					<div class="details">
					
						<div class="detail">
							<h2><a href="#">Station Shop</a></h2>
                            <p><a href="http://www.templatemo.com/">Station Shop</a> is free css template provided by <a href="http://www.templatemo.com/">templatemo.com</a> for your personal or commercial websites. Sed aliquam arcu. Donec urna massa, cursus et mattis at, mattis quis lectus. </p>
							<a href="#" title="Read more" class="more">Read more</a>
						</div><!-- /detail -->
						
						<div class="detail">
							<h2><a href="#">Fusce hendrerit</a></h2>
                            <p>Duis dignissim tincidunt turpis eget pellentesque. Nulla consectetur accumsan facilisis. Suspendisse et est lectus, at consectetur sem.</p>
							<a href="#" title="Read more" class="more">Read more</a>
						</div><!-- /detail -->
						
						<div class="detail">
							<h2><a href="#">Aenean massa cum</a></h2>
                            <p>Sed vel interdum sapien. Aliquam consequat, diam sit amet iaculis ultrices, diam erat faucibus dolor, quis auctor metus libero vel mi.</p>
							<a href="#" title="Read more" class="more">Read more</a>
						</div><!-- /detail -->
						
					</div><!-- /details -->
					
				</div><!-- /details_wrapper -->
				
				<div class="paging">
					<div id="numbers"></div>
					<a href="javascript:void(0);" class="previous" title="Previous" >Previous</a>
					<a href="javascript:void(0);" class="next" title="Next">Next</a>
				</div><!-- /paging -->
				
				<a href="javascript:void(0);" class="play" title="Turn on autoplay">Play</a>
				<a href="javascript:void(0);" class="pause" title="Turn off autoplay">Pause</a>
				
			</div><!-- /panel -->
	
			<div class="backgrounds">
				
				<div class="item item_1">
					<img src="images/slider/02.jpg" alt="Slider 01" />
				</div><!-- /item -->
				
				<div class="item item_2">
					<img src="images/slider/03.jpg" alt="Slider 02" />
				</div><!-- /item -->
				
				<div class="item item_3">
					<img src="images/slider/01.jpg" alt="Slider 03" />
				</div><!-- /item -->
				
			</div><!-- /backgrounds -->
    </div> <!-- END of templatemo_middle -->
    
    <div id="templatemo_main">
   		<div id="sidebar" class="float_l">
        	<div class="sidebar_box"><span class="bottom"></span>
            	<h3>Categorias<a href="http://es.forwallpaper.com"  class="more_link"  ></a></h3>   
                <div class="content"> 
                	<ul class="sidebar_list">
                           
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
            	<h3>Sugerencias <a href="http://es.forwallpaper.com"  class="more_link"  ></a></h3>   
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
        	<h1>Productos</h1>
                    <%@page import ="Logica.*" %>
                    <%
                    Logica.Categoria cate = new Logica.Categoria();
                    java.util.ArrayList<Producto> listaProductos = new java.util.ArrayList<Producto>();
                    listaProductos = cate.ObtenerProductos(request.getParameter("cat"));
                    int largo2 = listaProductos.size();
                    if (largo2>10&&request.getParameter("exp").equals("0") ){largo2=10;
                    %>
                    <a href="Catalogo.jsp?cat=<%=request.getParameter("cat")%>&exp=1" class="detail">Ver mas de 10 Productos</a>
                    <%
                    }
                    for(int i = 0; i < largo2; i++){
                    %>
            <div class="product_box">
                <%String H = listaProductos.get(i).ID_PRODUCTO+"";%>
            	<a href="Producto.jsp?id=<%=H%>"><img src="images/product/<%=listaProductos.get(i).NOMBRE%>.jpg" alt="Image 01" /></a>
                <h3><%=listaProductos.get(i).NOMBRE%></h3>
                <p class="product_price">Q <%=listaProductos.get(i).PRECIO%></p>
                <a  href="Producto.jsp?id=<%=H%>"  class="detail">Detalles</a>
            </div> 
                    <%
                    }
                    %>
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
</html>