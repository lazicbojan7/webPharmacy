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
                      $("#s").hide();
    $('#tabela').DataTable();
    $("#storno").click(function () {
        $("#s").show();
    });
});
   <%--show hide iznad--%>
            </script>
                 
                  <script>
            $(document).ready(function () {
                $("#ok").click(function () {

                    $.post("servletok",
                            {
                                racun: document.getElementById("racun").value,
                                kupac: document.getElementById("kupac").value,
                                apoteka: document.getElementById("apoteka").value
                            },
                            function (data, status) {

                                // document.getElementById("res").innerHTML = data;
                                alert("Data: " + data + "\nStatus: " + status);
                                // document.getElementById("myForm").submit(); 
                            });
                });
            });
        </script>
                 
                 
                        
                       
        
    </head>
    <body>
         <div id="omotac">
             <div id="levi">
                 <table id="tabela">
              <thead>
            <th>Kupac</th>
            <th>Datum</th>
            <th>Iznos</th>
            <th></th>
              

            </thead>
              <c:forEach items="${racuni}" var="r">
                  <tr>
                      <td>${r.kupId.kupNaziv}</td>
                      <td><fmt:formatDate pattern = "yyyy.MM.dd" value = "${r.racDatum}" /></td>
                      <td>${r.racIznos}</td>
                      <td><form action="arhiva">
                           <input type="hidden" name="racun" value="${r.racId}">   
                            <input type="submit" value="Vidi stavke">
                          </form></td>
                      </tr>
                       </c:forEach>
                      </table>
                  
                    <a href="index" class="btn btn-warning btn-lg active" role="button">Kraj rada-napusti arhivu</a>
             </div>
             <div id="desni">
                   <c:if test="${izbor}">
  <fieldset>
    <legend>Stavke racuna:</legend>
                <table border="1">
                     <thead>        
            <th>Artikal:</th>
            <th>Kolicina:</th>
            <th>Cena:</th>
            </thead>
              <c:forEach items="${stavke}" var="s">
                  <tr><td>${s.artId.artNaziv}</td> <td>${s.staKolicina}</td> <td>${s.staCena}</td></tr>
            </c:forEach>
                </table>
  </fieldset>
              </c:if>
                 
                  <c:if test="${izbor}">
                          <button type="button" class="btn btn-danger" id="storno">Ponisti racun-vrati robu</button>
                                  
                          <form id="s">
                               <fieldset>
                           <legend>Storniranje:</legend>
                      <label id="label1" for="apoteka">Izaberi apoteku za povracaj robe</label>
                    <select id="apoteka" name="apoteka">
            <option disabled selected value> -- Apoteke -- </option>
            <c:forEach items="${apoteke}" var="a">
                <option value="${a.apoId}">${a.apoNaziv}</option>
            </c:forEach>
             </select>
                      <input type="hidden" name="racun" id="racun" value="${izabran.racId}">
                        <input type="hidden" name="kupac" id="kupac" value="${kk.kupId}">
                        <button type="button" id="ok">Potvrdi</button>
                               </fieldset>
                 </form>
                  
                  </c:if>
                         
               
                
                 
                
             </div>
         </div>
    </body>
</html>
