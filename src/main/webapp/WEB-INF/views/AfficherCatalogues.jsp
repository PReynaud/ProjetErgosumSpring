<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Affichage de tous les catalogues</title>

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
    <div class="row">
        <form method="get" action="afficherCatalogues">
            <div class="col-sm-3">
                <select name="anneeDebut" id="anneeDebut">
                    <c:forEach items="${catalogues}" var="item">
                        <option value="${item.id}">${item.id}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-3">
                <select name="anneeFin" id="anneeFin">
                    <c:forEach items="${catalogues}" var="item2">
                        <option value="${item2.id}">${item2.id}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-3">
                <input type="submit" value="Filtrer"/>
            </div>
        </form>
    </div>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Numero</th>
                <th>Quantité distribuée</th>
                <th>Quantité</th>
                <th>Différence</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${mesCataloguesQuantites}" var="item3">
                <tr>
                    <td>${item3.id}</td>
                    <td>${item3.quantiteDistribuee}</td>
                    <td>${item3.quantite}</td>
                    <td>${item3.quantiteDistribuee}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page='footer.jsp' flush="true"/>
</body>
</html>
