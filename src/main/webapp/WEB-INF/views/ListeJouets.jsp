<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Affichage de tous les jouets</title>

    <link rel="stylesheet" href="resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="resources/css/main.css">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery-ui.js" type="text/javascript"></script>
    <script src="resources/js/main.js" type="text/javascript"></script>
    <script src="resources/js/button.js" type="text/javascript"></script>
</head>
</head>
<body>
<jsp:include page='header.jsp' flush="true"/>

<div class="row first-row">
    <div class="circle-buttons">
        <div class="circle-button red">
            <span class="glyphicon glyphicon-remove"></span>
        </div>
        <div class="circle-button green">
            <span class="glyphicon glyphicon-pencil"></span>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Numero</th>
                <th>Libellé</th>
                <th>Code catégorie</th>
                <th>Code tranche d'âge</th>
                <th>Modifier/Supprimer</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${mesJouets}" var="item">
                <tr>
                    <td>${item.numero}</td>
                    <td>${item.libelle}</td>
                    <td>${item.getCategorie().getCodecateg()}</td>
                    <td>${item.getTrancheage().getCodetranche()}</td>
                    <td>
                        <div class="checkbox"><input type="checkbox" value="${item.numero}"></div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page='footer.jsp' flush="true"/>
</body>
</html>
