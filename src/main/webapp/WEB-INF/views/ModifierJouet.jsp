<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Modifier un jouet</title>

    <link rel="stylesheet" href="resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="resources/css/main.css">
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery-ui.js" type="text/javascript"></script>
    <script src="resources/js/main.js" type="text/javascript"></script>
    <script src="resources/js/lines.js" type="text/javascript"></script>
</head>

<body>
<jsp:include page='header.jsp' flush="true"/>

<div class="row first-row">
    <h1>Formulaire de modification d'un jouet</h1>

    <input type ="hidden" name="uneErreur"  value="${MesErreurs}"  id ="id_erreur" >
    <form method="get" action="sauverJouet">
        <input type="hidden" name="type" value="ajout"  id="type"/>
        <div class="row">
            <div class="row">
                <div class="col-sm-2">Numéro</div>
                <div class="col-sm-2">
                    <input type="text" name="id" id="id" value="${jouet.numero}" placeholder="Numéro du jouet" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Libellé</div>
                <div class="col-sm-2">
                    <input type="text" name="libelle" id="libelle" value="${jouet.libelle}" placeholder="Libellé du jouet" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Catégorie</div>
                <div class="col-sm-2">
                    <select name="codecateg" id="codecateg">
                        <c:forEach items="${categories}" var="item">
                            <option value="${item.getCodecateg()}"
                                    <c:if test="${item.getCodecateg() == jouet.categorie.getCodecateg()}">
                                        selected="selected"
                                    </c:if>
                                >${item.getLibcateg()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">Tranche d'âge</div>
                <div class="col-sm-2">
                    <select name="codetranche" id="codetranche">
                        <c:forEach items="${tranches}" var="item">
                            <option value="${item.getCodetranche()}"
                                    <c:if test="${item.getCodetranche() == jouet.trancheage.getCodetranche()}">
                                        selected="selected"
                                    </c:if>
                            >${item.getCodetranche()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="root-catalogues">
                ${jouet.getTailleComportes()}
                <c:forEach var="i" begin="1" end="${jouet.getTailleComportes()}">
                    <div class="row separate-field">
                        <div class="row">
                            <div class="col-sm-2">Catalogues</div>
                            <div class="col-sm-2">
                                <select class="selectCatalogue" name="codecatalogue1" id="codecatalogue1">
                                    <c:forEach items="${catalogues}" var="item">
                                        <option value="${item.getAnnee()}">${item.getAnnee()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">Quantités distribuées</div>
                            <div class="col-sm-2">
                                <input class="quantiteCatalogue" type="text" value=""
                                       name="quantiteDistribution1" id="quantiteDistribution1"
                                       placeholder="Quantité distribuée" required/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </form>
</div>

<jsp:include page='footer.jsp' flush="true"/>
</body>
</html>
