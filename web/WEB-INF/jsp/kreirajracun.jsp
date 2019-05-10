<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <%--link za eksterni css:--%>
        <link rel="stylesheet" type="text/css" href="../style.css">
        
         <%-- BOOTSTRAP zahteva ove 3 biblioteke--%>
<!-- 1 Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 2 jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- 3 Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

 
                 
               

        <!--ovo dugme bi koristio
      <button type="button" class="btn btn-primary" id="dugme"><span class="glyphicon glyphicon-ok"></span>Potvrdi</button>  
        <script>
            $(document).ready(function () {
                $("#obavestenje").hide();
                $("#dugme").click(function () {
                   
                    $.post("servletnoviracun",
                            {
                                
                                kupac: document.getElementById("aaa").value,
                                apoteka: document.getElementById("bbb").value
                               
                            },
                            function (data, status) {

                                document.getElementById("res").innerHTML = data;
                                alert("Data: " + data + "\nStatus: " + status);
                                // document.getElementById("myForm").submit(); 
                            });

                });
            });
        </script>
        -->
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
      
                <p id="datum">${datum}</p>
                <p>Izaberite kupca i apoteku u kojoj se nalazite:</p> 
                <form action="kreirajstavke" method="post">
                <select id="aaa" name="kupac">
            <option disabled selected value> -- Izaberi kupca -- </option>
            <c:forEach items="${kupci}" var="k">
                <option value="${k.kupId}">${k.kupNaziv}</option>
            </c:forEach>
             </select>
                    
                <select id="bbb" name="apoteka">
            <option disabled selected value> -- Izaberi apoteku -- </option>
            <c:forEach items="${apoteke}" var="a">
                <option value="${a.apoId}">${a.apoNaziv}</option>
            </c:forEach>
             </select>    
                 <input type="submit" value="Potvrdi"> 
            </form>
                
            
           
            
            
           
            
        </div>
    </body>
</html>
