

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveri stanje</title>
          <%--link za eksterni css:--%>
        <link rel="stylesheet" type="text/css" href="../style.css">
        
         <%-- BOOTSTRAP zahteva ove 3 biblioteke--%>
<!-- 1 Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 2 jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- 3 Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

 <%--linkovi za datatables:--%>
          <%--1.css za datatables--%>
           <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
            <%--2.javascript za datatables(ovaj nije samozatvrajuci)--%>
            <script src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
             <%--3. obavezna je i ova skripta koja sve to pokrece(tabela se mora isto zvati)--%>
             <script>
                 $(document).ready(function(){
    $('#tabela').DataTable();
});
                 </script>
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
        <h1>Provera stanja</h1>
 <form id="unos" action="proveristanje" method="post">
     
        
        <select name="apoteka">
            <option disabled selected value> -- Izaberi jednu od apoteka -- </option>
            <c:forEach items="${apoteke}" var="a">
                <option value="${a.apoId}">${a.apoNaziv}</option>
            </c:forEach>
             </select>
        
          <input type="submit" value="Potvrdi">
         
            </form>
      <c:if test="${izbor}">
          <h3>Kolicine u apoteci <strong>${trazena.apoNaziv}</strong> su sledece:</h3>
          
          <table id="tabela">
              <thead>
            <th>Naziv artikla</th>
             <th>Kolicina</th>
              <th>Cena</th>
              <th></th>

            </thead>
              <c:forEach items="${stanja}" var="s">
                  <tr>
                      <td>${s.artId.artNaziv}</td>
                      <td>${s.staKolicina}</td>
                      <td>${s.artId.artCena}</td>
                      <td><form action="izmenistanje">
                           <input type="hidden" name="apoX" value="${trazena.apoId}">   
                           <input type="hidden" name="artX" value="${s.artId.artId}">
                           <input type="hidden" name="staX" value="${s.staId}">
                            <input type="submit" value="Izmeni">
                          </form></td>
                      
                </tr>
            </c:forEach>
              </c:if>
          </table>
        </div>
    </body>
</html>
