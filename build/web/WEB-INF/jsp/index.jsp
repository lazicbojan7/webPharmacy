<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pocetna</title>
        
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


  <script>
                 $(document).ready(function(){
   
      $("#unos").hide();

   $("#show").click(function(){
        $("#unos").show();
    });
     $("#hide").click(function(){
        $("#unos").hide();
    });
});
                 </script>
                 
                 <!--Skripta koja ce da pokazuje skrivenu formu za promenu lozinke je ubacena iznad -->


    </head>

    <body>
        <div id="main">
            <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Web Apoteka</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="index">Pocetna</a></li>
      <li><a href="apoteke">Apoteke</a></li>
      <li><a href="artikli">Artikli</a></li>
      <li><a href="dobavljaci">Dobavljaci</a></li>
       <li><a href="proizvodjaci">Proizvodjaci</a></li>
        <li><a href="kupci">Kupci</a></li>
         <li><a href="zaposleni">Zaposleni</a></li>
    </ul>
  </div>
</nav>
            <a href="proveristanje" class="btn btn-primary btn-lg active" role="button">Proveri/izmeni stanje u apotekama</a>
            
            <a href="kreirajracun" class="btn btn-primary btn-lg active" role="button">Kreiraj racun</a>
            
             <a href="arhiva" class="btn btn-primary btn-lg active" role="button">Arhiva racuna</a>
            
            
            
            <h1>Dobrodosli!</h1>
                             <c:choose>
                            <c:when test="${ulogovan==true}">
              <p>Ulogovani ste kao ${zaposleni.zapIme} ${zaposleni.zapPrezime} </p>
              <%--<p><strong><a href="logout">Izlogujte se!</a></strong></p>--%>
              <a href="logout" class="btn btn-warning btn-lg active" role="button">Izlogujte se ovde!</a>
                 <button type="button" id="show" class="btn btn-default">Izmenite vasu lozinku</button>
              </c:when>
              <c:otherwise>
                     <div class="alert alert-info">
  <strong>Niste ulogovani!</strong> Da bi ste imali pristup svim funkcionalnostima
  potrebno je da se ulogujete kao zaposleni ili administrator.
</div>
                  <%--<p><strong><a href="login">Ulogujte se ovde!</a></strong></p>--%>
                      <a href="login" class="btn btn-warning btn-lg active" role="button">Ulogujte se ovde!</a>
              </c:otherwise>
                             </c:choose>
                     
             <fieldset><legend></legend>
                     <form id="unos" action="izmenalozinke" method="post">
                 <input type="password" name="trenutna" placeholder="Trenutna lozinka">
                 <input type="password" name="nova" placeholder="Nova lozinka">
                 <p>Ponovite novu lozinku:</p>
                  <input type="password" name="nova1" placeholder="Nova lozinka">
         
          <input type="submit" value="Potvrdi">
          <button type="button" id="hide" class="btn">Odustani</button>
            </form>
                     </fieldset>                       
                    
        </div>
    </body>
</html>
