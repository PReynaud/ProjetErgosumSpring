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
      <c:forEach items="${dictionnaire}" var="item">
        <tr>
          <td>${item.id}</td>
          <td>${item.quantiteDistribuee}</td>
          <td>${item.quantite}</td>
          <td>${item.quantiteDistribuee}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
<jsp:include page='footer.jsp' flush="true"/>
</body>
</html>
