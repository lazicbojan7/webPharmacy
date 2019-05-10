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
                 
                 <script>
                     <%--funkcija dodaj koja se pokrece klikom na dugme u tabeli--%>
                      function dodaj(id, naziv){
 $("ol").append("<li>"+naziv+"</li>");
 $("#aaa").text(naziv);
 $("#stanje").val(id);
 
 
 $("#label1").show();
 $("#label2").show();
 $("#label3").show();
 $("#aaa").show();
 $("#kolicina").show();
 $("#ok").show();
 $('#kolicina').prop('selectedIndex',0);
  document.getElementById("forma").reset();
}
                     
            $(document).ready(function () {
                $("#label1").hide();
                $("#label2").hide();
                $("#label3").hide();
                $("#aaa").hide();
                $("#kolicina").hide();
                $("#ok").hide();
              
                <%--Klikom na ok podaci se salju na servlet--%>
                $("#ok").click(function () {
                

                    $.post("servletstavke",
                            {
                                racun: document.getElementById("racun").value,
                                apoteka: document.getElementById("apoteka").value,
                                kupac: document.getElementById("kupac").value,
                                kolicina: document.getElementById("kolicina").value,
                                stanje: document.getElementById("stanje").value
                            },
                            function (data, status) {

                                document.getElementById("res").innerHTML = data;
                                alert("Data: " + data + "\nStatus: " + status);
                                // document.getElementById("myForm").submit(); 
                            });
                  <%--takodje klikom na ok sakrivaju se ovi elementi--%>          
                $("#label1").hide();
                $("#label2").hide();
                $("#label3").hide();
                $("#aaa").hide();
                $("#kolicina").hide();
                $("#ok").hide();         
                            
                            
                });
            });
        </script>
        
                  <script>
                       $(document).ready(function () {
                             $("#stavke").hide();
                              $("#sakrij").hide();
                            $("#prikazi").click(function () {
                                $("#stavke").show();
                                $("#sakrij").show();
                                 $("#prikazi").hide();
                                     });
                                      $("#sakrij").click(function () {
                                $("#stavke").hide();
                                 $("#prikazi").show();
                                  $("#sakrij").hide();
                                     });
            });
                            
                       
                        </script>

    </head>
    <body>
        <div id="omotac">
             <div id="left">
         
           
                     
             
          <table id="tabela">
              <thead>
            <th>Naziv</th>
            <th>Cena</th>
            <th><strong>Stanje</strong></th>
            <th></th>
              

            </thead>
              <c:forEach items="${stanja}" var="s">
                  <tr>
                      <td>${s.artId.artNaziv}</td>
                      <td>${s.artId.artCena}</td>
                      <td>${s.staKolicina}</td>
                      <td><button type="button" id="dugme" onclick="dodaj(${s.staId},'${s.artId.artNaziv}')" >Dodaj</button></td>
                      </tr>
                       </c:forEach>
                      </table>
          
                </div>
             <div id="right">
                 
                   <p><strong>Kreirali ste racun u apoteci ${a.apoNaziv}
                 za kupca ${k.kupNaziv} Sada dodajte artikle klikom na dugme "Dodaj".</strong>
             </p>
             <div class="alert alert-info">
                 <strong>Obavestenje!</strong> <p id="res">Nema dodatih stavki</p>
  </div>
            
             
           <p><div class="alert alert-danger">
  <strong>Paznja!</strong> Maksimalan broj komada jednog artikla koji prodajete ne sme biti
  veci od kolicine tog artikla na stanju u tabeli pored!
               </div></p>
                 
               
               <fieldset>
    <legend>Dodavanje nove stavke:</legend> 
    <form id="forma">
                              <input type="hidden" name="racun" id="racun" value="${r.racId}">
                              <input type="hidden" name="apoteka" id="apoteka" value="${a.apoId}">  
                              <input type="hidden" name="kupac" id="kupac" value="${k.kupId}">
                              <input type="hidden" name="stanje" id="stanje" value="">
                              <label for="aaa" id="label1">Izabrali ste artikal:</label>
                              <p id="aaa"></p>
                              <label for="kolicina" id="label2">Odaberite kolicinu:</label>
                              <input type="number" name="kolicina" id="kolicina" min="1" >
                              <%--<select name="kolicina" id="kolicina">
                                  
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                            </select>--%>
                        <label for="ok" id="label3">Potvrdite klikom na "OK"</label>
                        <input type="button" id="ok" value="Ok!"> 
                      </form>
                              
                              <%--<button type="button" id="ok">OK</button>--%>
               </fieldset> 
                             
       <fieldset>
    <legend>Prikaz stavki:</legend>                       
                              <button type="button" id="prikazi" >Prikazi stavke</button>
                              <button type="button" id="sakrij" >Sakrij stavke</button>
                              
                              
                              <ol id="stavke">
                                  
                              </ol>
       </fieldset>
            <a href="index" class="btn btn-warning btn-lg active" role="button">Kraj rada-zavrsi racun</a>
             </div>
             
        </div>
    </body>
</html>
