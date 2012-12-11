<%-- 
    Document   : documentos
    Created on : 14-nov-2012, 13:59:32
    Author     : VINCE
--%>
<%@page import="Pojos.DocumentoPojo"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    LinkedList<DocumentoPojo> lista = (LinkedList) request.getAttribute("tabla");
    int idActividadOculta = Integer.parseInt(request.getParameter("idActividadOculta"));
    int n = lista.size();
    HttpSession sesion = request.getSession();
    String idUsuario = sesion.getAttribute("id").toString();
    String usuario = (String) sesion.getAttribute("nombre");
    String apellido = (String) sesion.getAttribute("ape1");
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Documentos</title>
        <link rel="Stylesheet" href="css.css" type="text/css">
    </head>
    <body>
        <div id="container">
            
                        <h3>Bienvenido <%=usuario%> <%=apellido%><br />
                Id. Usuario: <%=idUsuario%></h3>

  
          <h3>Documentos</h3><br/>



            <table>
                <tr>
                    <th>Id</th>
                    <th>Titulo</th>
                    <th>Contenido</th>
                    <th>Fecha</th>
                    
                    <th>Etiquetas</th>
                    
                    
                    <th>Entrega</th>
                </tr>

                <% for (int i = 0; i < n; i++) {

                 boolean privado = lista.get(i).getPrivado();
                 if (privado == true){
                    %>
                 <tr class="oculta">
                    <td><%out.println(lista.get(i).getId());%></td>
                    <td><%out.println(lista.get(i).getTitulo());%></td>
                    <td><%out.println(lista.get(i).getContenido());%></td>
                    <td><%out.println(lista.get(i).getFecha());%></td>
                    
                    <td><%out.println(lista.get(i).getEtiquetas());%></td>
                    
                    

                    <%}else{ %>
                <tr>
                    <td><%out.println(lista.get(i).getId());%></td>
                    <td><%out.println(lista.get(i).getTitulo());%></td>
                    <td><%out.println(lista.get(i).getContenido());%></td>
                    <td><%out.println(lista.get(i).getFecha());%></td>
                    
                    <td><%out.println(lista.get(i).getEtiquetas());%></td>
                    
                    
                    <%int idDocumento = lista.get(i).getId();%>


                    <td>
                        <form name="mantenimientoEntregas" action="MantenimientoEntregas" >
                            <input  type="submit" class="boton2" value="Finalizar Entrega" />
                            <input type="hidden" name="idDocumentoOculta" value="<%=idDocumento%>">
                            <input type="hidden" name="idActividadOculta" value="<%=idActividadOculta%>">


                        </form>

                    </td>
                    <%} %>
                        
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
