<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Ajout d'un jouet</title>

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
    <h1>Formulaire d'ajout d'un jouet</h1>

    <input type ="hidden" name="uneErreur"  value="${MesErreurs}"  id ="id_erreur" >
    <form method="get" action="sauverJouet">
        <input type="hidden" name="type" value="ajout"  id="type"/>
        <div class="row">
            <div class="row">
                <div class="col-sm-2">Numéro</div>
                <div class="col-sm-2">
                    <input type="text" name="id" id="id" placeholder="Numéro du jouet" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Libellé</div>
                <div class="col-sm-2">
                    <input type="text" name="libelle" id="libelle" placeholder="Libellé du jouet" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Catégorie</div>
                <div class="col-sm-2">
                    <select name="codecateg" id="codecateg">
                        <c:forEach items="${categories}" var="item">
                            <option value="${item.getCodecateg()}">${item.getLibcateg()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Tranche d'âge</div>
                <div class="col-sm-2">
                    <select name="codetranche" id="codetranche">
                        <c:forEach items="${tranches}" var="item">
                            <option value="${item.getCodetranche()}">${item.getCodetranche()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Catalogues</div>
                <div class="col-sm-2">
                    <select name="codecatalogue" id="codecatalogue">
                        <c:forEach items="${catalogues}" var="item">
                            <option value="${item.getAnnee()}">${item.getAnnee()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Quantités distribuées</div>
                <div class="col-sm-2">
                    <input type="text" name="quantiteDistribution" id="quantiteDistribution" placeholder="Quantité distribuée" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-2">
                    <input type="submit" name="ajouter" value="Ajouter"/>
                </div>
            </div>

        </div>
    </form>
</div>

<jsp:include page='footer.jsp' flush="true"/>
</body>
</html>
