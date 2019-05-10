
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>izmeni artikal</title>
    </head>
    <body>
        <p>Izmenite podatke koje zelite:</p>
       <form action="potvrdaizmeniartikal" method="post">
           <label>Naziv: <input type="text" name="naziv" value="${artikal.artNaziv}"></label>
          <label>Tip: <input type="text" name="tip" value="${artikal.artTip}"></label>
          <label>Opis: <input type="text" name="opis" value="${artikal.artOpis}"></label>
           <label>Cena: :<input type="text" name="cena" value="${artikal.artCena}"></label>
           <input type="hidden" name="id" value="${artikal.artId}">
             <input type="submit" value="Potvrdi izmene">
        </form>
    </body>
</html>
