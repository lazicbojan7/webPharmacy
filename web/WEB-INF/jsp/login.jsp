<%-- 
    Document   : login
    Created on : Aug 8, 2017, 11:32:48 PM
    Author     : Bojan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
         <!-- putanja sa neta za css-->
   <!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,700">-->
    <!-- poseban css samo za ovu stranicu-->
<link href="../loginCSS.css" type="text/css" rel="stylesheet">
    </head>
    <body>
          <div id="login">

        <form name='form-login' action ="login" method="post">
        <span class="fontawesome-user"></span>
          <input type="text" name="email" placeholder="Vas email">
       <!--uz pomoc ovog input type password i fontawesome-lock sifra se prikazuje u tackama-->
        <span class="fontawesome-lock"></span>
          <input type="password" name="pass" placeholder="Vasa sifra">
        
        <input type="submit" value="Login">

</form>
      
    </div>
    </body>
</html>
