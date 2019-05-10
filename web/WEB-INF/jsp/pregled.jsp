
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>
    <body>
    <div id="main">
    <p>Pregled</p>
        <form id="unos" action="pregled" method="post">
     
        
        <select name="apoteka">
            <option disabled selected value> -- Izaberi jednu od apoteka -- </option>
            <c:forEach items="${apoteke}" var="a">
                <option value="${a.apoId}">${a.apoNaziv}</option>
            </c:forEach>
             </select>
        
          <input type="submit" value="Potvrdi">
         
            </form>
            </div>
    </body>
</html>
