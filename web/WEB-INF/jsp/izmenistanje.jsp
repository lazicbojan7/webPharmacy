
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Izmeni stanje</title>
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
            
            <p> <strong>Apoteka:</strong> ${apoteka.apoNaziv} <strong>Artikal:</strong> ${artikal.artNaziv} <strong>Stanje:</strong> ${stanje.staKolicina}</p>
                    
        <h2>Unesi novo stanje:</h2>
        <form action="potvrdastanje" method="post">
            <input type="text" name="novoStanje" placeholder="Upisi novo stanje"> 
            <input type="hidden" name="apoteka" value="${apoteka.apoId}">   
            <input type="hidden" name="artikal" value="${artikal.artId}">
             <input type="hidden" name="stanje" value="${stanje.staId}">
             <input type="submit" value="Potvrdi">
        </form>
        </div>
    </body>
</html>
