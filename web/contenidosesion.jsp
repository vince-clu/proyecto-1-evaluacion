<%-- 
    Document   : 
    Created on : 
    Author     : VINCE
--%>


<%@page import="Pojos.ActividadPojo"%>
<%@page import="javax.swing.JTable"%>
<%@page import="Daos.ActividadDao"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String usuario = "";
    String apellido = "";
    HttpSession Session = request.getSession();

    
   
    

    usuario = (String) Session.getAttribute("nombre");
    apellido = (String) Session.getAttribute("ape1");
    String idUsuario= Session.getAttribute("id").toString();

    LinkedList<ActividadPojo> lista = (LinkedList) request.getAttribute("tabla");

    int n = lista.size();
%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Private</title>

    </head>
    <body>

        <h3>Bienvenido <%=usuario%> <%=apellido%><br />
            Id. Usuario: <%=idUsuario%></h3>
        

        <form method="post" action="CerrarSesion">
            <input type="submit" value="Cerrar Sesión">


        </form>





        <h3>Actividades</h3><br/>


        <table border="1">
            <tr>
                <th>Id</th>
                <th>Enunciado</th>
                <th>Fecha</th>
                <th>Evaluacion</th>
                <th>Activo</th>
                <th>Documento</th>
            </tr>

            <% for (int i = 0; i < n; i++) {

            %> <tr>
                <td><%out.println(lista.get(i).getId());%></td>
                <td><%out.println(lista.get(i).getEnunciado());%></td>
                <td><%out.println(lista.get(i).getFecha());%></td>
                <td><%out.println(lista.get(i).getEvaluacion());%></td> 
                <td><%out.println(lista.get(i).getActivo());%></td>
                <%int j = lista.get(i).getId();%>
                
                <td>
                    <form name="contenido" action="Servlet2" method="post">
                        
                        <input type="hidden" value="<%=j%>" name="idActividadOculta" />
                        <input type="hidden" value="<%=idUsuario%>" name="idUsuarioOculta" />   
                        <input type="submit" value="Entregar">
                        
                    </form>
                </td>
            </tr>
            <%}%>


        </table>

    </body>
</html>
