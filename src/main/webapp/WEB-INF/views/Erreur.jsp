<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestion des erreurs</title>

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
    <input type ="hidden" name="uneErreur"  value="${MesErreurs}"  id ="id_erreur" >
    <h3>  Une erreur est survenue</h3>

    <img src="resources/images/jigsaw.jpg" />
    <h4>"Nous allons jouer à un jeu"</h4>
    <p><a href="Accueil">Retour à la page principale</a></p>
</div>
<jsp:include page='footer.jsp' flush="true"/>
</body>
</html>