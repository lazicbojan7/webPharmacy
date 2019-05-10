
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Potvrda</title>
          <%--jquery obican:--%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    
           <%--link za eksterni css--%>
        <link rel="stylesheet" type="text/css" href="../style.css">
        
        <%-- BOOTSTRAP zahteva ove 3 biblioteke--%>
<!-- 1 Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 2 jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- 3 Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
          <div id="main">
               <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Web Apoteka</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="index">Pocetna</a></li>
      <li><a href="apoteke">Apoteke</a></li>
      <li><a href="artikli">Artikli</a></li>
      <li><a href="dobavljaci">Dobavljaci</a></li>
       <li><a href="proizvodjaci">Proizvodjaci</a></li>
        <li><a href="kupci">Kupci</a></li>
         <li><a href="zaposleni">Zaposleni</a></li>
    </ul>
  </div>
</nav>
         <div class="alert alert-success">
    <strong>Vas unos je uspesno sacuvan!</strong>${poruka}</div>
    
          </div>
    </body>
</html>
