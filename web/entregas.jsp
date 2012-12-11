<%-- 
    Document   : entregas
    Created on : 19-nov-2012, 15:46:24
    Author     : vesprada
--%>

<%@page import="Pojos.EntregaPojo"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    LinkedList<EntregaPojo> lista = (LinkedList) request.getAttribute("tabla");

    int n = lista.size();
    String idUsuario = sesion.getAttribute("id").toString();
    String usuario = (String) sesion.getAttribute("nombre");
    String apellido = (String) sesion.getAttribute("ape1");
    int id_documento = Integer.parseInt(request.getParameter("idDocumentoOculta"));

    int id_actividad = Integer.parseInt(request.getParameter("idActividadOculta"));

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entregas Realizadas</title>
        <link rel="Stylesheet" href="css.css" type="text/css">
    </head>

    <body>
        <div id="container">
            <h3>Bienvenido <%=usuario%> <%=apellido%><br />
                Id. Usuario: <%=idUsuario%></h3>

            <h2>Actividades entregadas</h2>

            <table>
                <tr>
                    <th>Id</th>
                    <th>Actividad</th>
                    <th>Documento</th>
                    <th>Fecha</th>

                </tr>

                <% for (int i = 0; i < n; i++) {
                %> <tr>
                    <td><%out.println(lista.get(i).getId());%></td>
                    <td><%out.println(lista.get(i).getId_actividad());%></td>
                    <td><%out.println(lista.get(i).getId_documento());%></td>
                    <td><%out.println(lista.get(i).getEntrega());%></td>
                    <%
                        int idEntregaOculta = lista.get(i).getId();
                    %>
                    <td>
                        <form name="borrar" action="Borrar" method="post">
                            <input class="boton" type="submit" value="Borrar" />
                            <input type="hidden" name="idEntregaOculta" value="<%=idEntregaOculta%>">
                            <input type="hidden" name="idDocumentoOculta" value="<%=id_documento%>">
                            <input type="hidden" name="idActividadOculta" value="<%=id_actividad%>">
                            <input type="hidden" name="tabla" value="<%=lista%>">
                        </form>

                    </td>

                </tr>
                <%}%>
            </table>

            <br />
            Última modificación: 
            <script type="text/javascript">
                document.write(document.lastModified)
                
            </script>
            <br />

        </div>
    </body>
</html>
