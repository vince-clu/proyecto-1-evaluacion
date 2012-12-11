<%-- 
    Document   : 
    Created on : 
    Author     : VINCE
--%>


<%@page import="com.sun.org.apache.bcel.internal.generic.BREAKPOINT"%>
<%@page import="Pojos.ActividadPojo"%>
<%@page import="javax.swing.JTable"%>
<%@page import="Daos.ActividadDao"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String usuario = "";
    String apellido = "";
    HttpSession sesion = request.getSession();
    
    String atras = (String) request.getAttribute("atras").toString();
    usuario = (String) sesion.getAttribute("nombre");
    apellido = (String) sesion.getAttribute("ape1");
    String idUsuario = sesion.getAttribute("id").toString();
    String pagina = (String) request.getAttribute("pagina").toString();
    

    LinkedList<ActividadPojo> lista = (LinkedList) request.getAttribute("tabla");

    int n = lista.size();
%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actividades</title>
        <link rel="Stylesheet" href="css.css" type="text/css">
    </head>
    <body>
        <div id="container">

            <h3>Bienvenido <%=usuario%> <%=apellido%><br />
                Id. Usuario: <%=idUsuario%></h3>


            <form method="post" action="CerrarSesion">
                <input class="boton" type="submit" value="Cerrar Sesión">


            </form>

            <h3>Actividades</h3><br/>


            <table>
                <tr>
                    <th>Id</th>
                    <th>Enunciado</th>
                    <th>Fecha</th>
                    <th>Evaluacion</th>
                    <th>Documento</th>
                </tr>

                <% for (int i = 0; i < n; i++) {

                %>

                <!-- Todo este codigo comentado, se utilizaba antes para ocultar ciertos datos que devolvia
                el resultset, simplemente los dejo por si cambia algo. Una vez este cerrada la aplicacion
                deberia de borrarlo-->

                <%
                    //  boolean activo = lista.get(i).getActivo();
                    // if (activo == false) {

                %>
                <!--  <tr class="oculta">
                      <td><%//out.println(lista.get(i).getId());%></td>
                      <td><%//out.println(lista.get(i).getEnunciado());%></td>
                      <td><%//out.println(lista.get(i).getFecha());%></td>
                      <td><%//out.println(lista.get(i).getEvaluacion());%></td> 
                -->

                <%//} else {%>           
                <tr>               
                    <td><%out.println(lista.get(i).getId());%></td>
                    <td><%out.println(lista.get(i).getEnunciado());%></td>
                    <td><%out.println(lista.get(i).getFecha());%></td>
                    <td><%out.println(lista.get(i).getEvaluacion());%></td> 

                    <%int j = lista.get(i).getId();%>

                    <td>
                        <form name="contenido" action="Documentos" method="post">

                            <input type="hidden" value="<%=j%>" name="idActividadOculta" />
                            <input type="hidden" value="<%=idUsuario%>" name="idUsuarioOculta" />   
                            <input class="boton" type="submit" value="Seleccionar">

                        </form>
                    </td>

                    <%//}%><!-- Fin del codigo comentado-->
                </tr>
                <%}%>


            </table>
            <br />

            Pagina nº: <%=pagina%><%=atras%>
            
            

            <form  action="Paginacion" method="post">
                
                <input class="boton2" type="submit" name="orden" value="Siguiente" />
                <% if (atras=="true" ){%>
                
                
                
                
                
                <input class="boton2" type="submit" name="orden" value="Atras" />
                
                <%}%>
                <input type="hidden" name="pagina" value="<%=pagina%>">
            </form>
            
                
        </div>
    </body>
</html>
