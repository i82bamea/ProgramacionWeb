<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean  id="customerBean" scope="session" class="JavaBeans.CustomerBean"></jsp:useBean>
<title>Creaci�n de Cr�ticas</title>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
	body{
		background: #9aafc7;
	   	font-family: 'Roboto', sans-serif;
	   	color: #fdfdfd;
	}
	.container {
    position:fixed;
    font-size: 1.2 em;
    text-align: center;
    vertical-align: center;
    top: 50%;
    left: 50%;
    width:44em;
    height:26em;
    margin-left: -22em; /*set to a negative number 1/2 of your width*/
    margin-top: -13em; /*set to a negative number 1/2 of your height*/
    border: 3px dotted #fdfdfd;
	}
	h1{
		font-size: 2.4em;
		text-align: center;
	}
	form button {
	 appearance: none;
	 outline: 0;
	 background-color: white;
	 border: 0;
	 padding: 20px 25px;
	 color: #9aafc7; 
	 border-radius: 3px;
	 width: 25%;
	 cursor: pointer;
	 font-size: 130%;
	  transition: transform .5s;
	}
 form button:hover {
	 transition-timing-function: ease-in-put;
	 background-color: #F4989C;
	 color: #fdfdfd;
	 transform: scale(1.1);
}  
</style>
</head>
<body>
    <jsp:include page="../CabeceraNavegacion.jsp"></jsp:include>
    <div class="container">
	<h1>�Est� seguro que quiere borrar su cuenta? Esta decisi�n es irrecuperable.</h1>
	<form method="post" action="../../../../Practica3/EliminarUsuarioServlet">
		<button type="submit" value="Borrar Usuario">Borrar Usuario</button>
	</form>
	</div>
</body>
</html>