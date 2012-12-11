<%-- 
    Document   : index
    Created on : 05-nov-2012, 18:00:08
    Author     : VINCE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <link rel="Stylesheet" href="css.css" type="text/css">
    </head>
    <body>
        <div id="container">
            <form action="Actividades" method="post">

                Usuario:<input type="text" name="user" autofocus>
                Password:<input type="password" name="pass">
                <input class="boton" type="submit" value="Login">

            </form>
        </div>
    </body>
</html>
