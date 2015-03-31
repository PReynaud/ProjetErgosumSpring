<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Affichage de tous les dictionnaires</title>

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
    <form method="get" action="afficherDictionnaire">
        <div class="row">
            <div class="form-group">
                <div class="col-sm-2">Année Catalogue</div>
                <div class="col-sm-4">
                    <select name="annee" id="annee">
                        <c:forEach items="${mesCatalogues}" var="item">
                            <option value="${item.id}"
                                    <c:if test="${anneecatalogue==item.id}">selected="selected"</c:if>>${item.id}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="btn btn-default">Recherche</button>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="row first-row">
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Catégorie</th>
                <th>Quantité</th>
            </tr>
            </thead>

            <tbody>
            <c:set var="total" value="${0}"/>
            <c:forEach items="${dictionnaire}" var="item">
                <tr>
                    <td>${item.key.libcateg}</td>
                    <td>${item.value}</td>
                    <c:set var="total" value="${total + item.value}"/>
                </tr>
            </c:forEach>
            <tr>
                <td>Total</td>
                <td>${total}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page='footer.jsp' flush="true"/>
</body>
</html>
