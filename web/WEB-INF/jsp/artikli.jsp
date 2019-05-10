

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Artikli</title>
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
                      console.log("eto to je to");
    $('#tabela').DataTable();
      $("#unos").hide();

   $("#show").click(function(){
        $("#unos").show();
    });
     $("#hide").click(function(){
        $("#unos").hide();
    });
});
                 </script>
                 
                 <!--Skripta koja ce da pokazuje skrivenu formu je ubacena iznad -->
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
      <li class="active"><a href="artikli">Artikli</a></li>
      <li><a href="dobavljaci">Dobavljaci</a></li>
       <li><a href="proizvodjaci">Proizvodjaci</a></li>
        <li><a href="kupci">Kupci</a></li>
         <li><a href="zaposleni">Zaposleni</a></li>
    </ul>
  </div>
</nav>
            <c:choose>
                            <c:when test="${ulogovan==true}">
              <p>Ulogovani ste kao ${zaposleni.zapIme} ${zaposleni.zapPrezime} </p>
              <button type="button" id="show" class="btn btn-default">Dodaj novi artikal</button>
              <p><strong><a href="logout">Izlogujte se!</a></strong></p>
              </c:when>
              <c:otherwise>
                     
                     <p><strong><a href="login">Ulogujte se ovde!</a></strong></p>
              </c:otherwise>
            </c:choose>
                     
                     
                     
                     <fieldset><legend></legend>
                     <form id="unos" action="potvrdaartikal" method="post">
             <h3>Unesite potrebne podatke o novom artiklu</h3>
             <select name="proizvodjac">
            <option disabled selected value> -- Izaberi proizvodjaca -- </option>
            <c:forEach items="${proizvodjaci}" var="p">
                <option value="${p.proId}">${p.proNaziv}</option>
            </c:forEach>
             </select>
           
            <select name="dobavljac">
             <option disabled selected value> -- Izaberi dobavljaca -- </option>
            <c:forEach items="${dobavljaci}" var="d">
                <option value="${d.dobId}">${d.dobNaziv}</option>
            </c:forEach>
             </select>
                         
       
        <input type="text" name="naziv" placeholder="Naziv">
        <input type="text" name="tip" placeholder="Tip">
         <input type="text" name="cena" placeholder="Cena">
        <input type="text" name="opis" placeholder="Opis">
       
       
          <input type="submit" value="Potvrdi">
          <button type="button" id="hide" class="btn">Odustani</button>
            </form>
                     </fieldset>
        <h1>Artikli</h1>

         <table id="tabela">
            <thead>
            <th>Naziv</th>
             <th>Cena</th>
              <th>Proizvodjac</th>
              <th>Dobavljac</th>
              <th>Tip</th>
              <th>Opis</th>
              <c:if test="${ulogovan == true}">
              <th></th>
              <th></th>

              </c:if>
            
            </thead>
            
            <c:forEach items="${artikli}" var="a">

               <tr>
                
                <td>${a.artNaziv}</td>
                <td>${a.artCena}</td>
                <td>${a.proId.proNaziv}</td>
                <td>${a.dobId.dobNaziv}</td>
                <td>${a.artTip}</td>
                <td>${a.artOpis}</td>
                <c:if test="${ulogovan == true}">
                <td><form action="izmeniartikal">
                           <input type="hidden" name="id" value="${a.artId}">   
                            <input type="submit" value="Izmeni">
                          </form></td>
                <td><form action="obrisiartikal">
                           <input type="hidden" name="id" value="${a.artId}">   
                            <input type="submit" value="Obrisi" style="font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px">
                          </form></td>          
                </c:if>
          
                    </tr>
          
            </c:forEach>
    
            
</table> 
        
        </div>
    </body>
</html>
