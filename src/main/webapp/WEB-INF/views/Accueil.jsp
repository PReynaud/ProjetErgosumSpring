<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@page import="java.util.Date,java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Accueil</title>

    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="resources/css/main.css">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery-ui.js" type="text/javascript"></script>
    <script src="resources/js/main.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page='header.jsp' flush="true"/>

<div class="row first-row">
    <h3>Gestion des jouets</h3>
    <h2> Nous sommes le <%= DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new Date()) %></h2>
    <h5>Gestion des jouets à partir d'une base de données.</h5>
    <h5>Cette application fonctionne à l'aide de Spring et tourne sous un serveur Tomcat.
        La vue a été réalisée à l'aide de Bootstrap, le JavaScript à l'aide de JQuery</h5>
</div>
<jsp:include page='footer.jsp' flush="true"/>

</body>
</html>