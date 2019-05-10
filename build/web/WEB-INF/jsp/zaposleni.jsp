

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zaposleni</title>
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
      <li><a href="artikli">Artikli</a></li>
      <li><a href="dobavljaci">Dobavljaci</a></li>
       <li><a href="proizvodjaci">Proizvodjaci</a></li>
        <li><a href="kupci">Kupci</a></li>
         <li class="active"><a href="zaposleni">Zaposleni</a></li>
    </ul>
  </div>
</nav>
            
             <c:choose>
                            <c:when test="${ulogovan==true}">
              <p>Ulogovani ste kao ${zaposleni.zapIme} ${zaposleni.zapPrezime} </p>
              <button type="button" id="show" class="btn btn-default">Dodaj novog zaposlenog</button>
              <p><strong><a href="logout">Izlogujte se!</a></strong></p>
              </c:when>
              <c:otherwise>
                     
                     <p><strong><a href="login">Ulogujte se ovde!</a></strong></p>
              </c:otherwise>
            </c:choose>
            
                      <fieldset><legend></legend>
                     <form id="unos" action="potvrdazaposleni" method="post">
             <h3>Unesite potrebne podatke o novom zaposlenom</h3>
         
       <input type="text" name="ime" placeholder="Ime">
       <input type="text" name="prezime" placeholder="Prezime">
         <input type="text" name="email" placeholder="Email">
         <input type="text" name="lozinka" placeholder="Lozinka">
           <select name="grad">
            <option disabled selected value> -- Izaberi grad -- </option>
            <c:forEach items="${gradovi}" var="g">
                <option value="${g.graId}">${g.graNaziv}</option>
            </c:forEach>
             </select>
          <select name="apoteka">
            <option disabled selected value> -- Izaberi apoteku -- </option>
            <c:forEach items="${apoteke}" var="a">
                <option value="${a.apoId}">${a.apoNaziv}</option>
            </c:forEach>
             </select>
         <select name="tip">
  <option disabled selected value> -- Izaberi tip zaposlenog -- </option>
  <option value="zaposleni">Zaposleni</option>
  <option value="admin">Admin</option>
       </select>
       
          <input type="submit" value="Potvrdi">
          <button type="button" id="hide" class="btn">Odustani</button>
            </form>
                     </fieldset>             
                     
        <h1>Zaposleni</h1>
         <table id="tabela">
            <thead>
            <th>Ime</th>
             <th>Prezime</th>
              <th>Radno mesto</th>
               <th>Grad</th>
              <th>Email</th>
              
            
            </thead>
            
            <c:forEach items="${zaposleni1}" var="z1">

                <tr>
                
                <td>${z1.zapIme}</td>
                <td>${z1.zapPrezime}</td>
                <td>${z1.apoId.apoNaziv}</td>
                <td>${z1.graId.graNaziv}</td> 
                <td>${z1.zapEmail}</td>
          
                    </tr>
            </c:forEach>
    
            
        </table>
        </div>
    </body>
</html>

