
<%--taglib regularan i taglib za vreme--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <form id="unos" action="strana" method="post">
          <select name="apoteka">
            <option disabled selected value> -- Izaberi jednu od apoteka -- </option>
            <c:forEach items="${apoteke}" var="a">
                <option value="${a.apoId}">${a.apoNaziv}</option>
            </c:forEach>
             </select>
        
          <input type="submit" value="Potvrdi">
         
            </form>
        
        <c:if test="${izbor}">
            <ol>
                  <c:forEach items="${zaposleni}" var="z">
                      <li>${z.zapIme}</li>
            </c:forEach>
                
            </ol>
            
        </c:if>
        
        
    </body>
</html>
