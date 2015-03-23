<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<header>
    <nav class="navbar navbar-inverse navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="Accueil.jsp">TP-Spring Informatique répartie</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-inverse navbar-nav">
                    <li><a href="Accueil">Accueil</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Jouets <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/ajouterJouet">Ajout</a></li>
                            <li><a href="/afficherJouets">Affichage de la liste</a></li>
                        </ul>
                    </li>
                    <li><a href="/afficherCatalogues">Catalogues</a></li>
                    <li><a href="/afficherDictionnaire">Dictionnaire</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>