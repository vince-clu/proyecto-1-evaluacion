/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Daos.ActividadDao;
import Daos.UsuarioDao;
import MySQL.MySql;
import Pojos.ActividadPojo;
import Pojos.UsuarioPojo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author VINCE
 */
@WebServlet(name = "Paginacion", urlPatterns = {"/Paginacion"})
public class Paginacion extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        UsuarioDao dao = new UsuarioDao();
        UsuarioPojo pojo = new UsuarioPojo();
        
        
            
        
        MySql con = new MySql();
                ArrayList<String>condiciones = new ArrayList();
                condiciones.add("activo=true");
                String pagina2="";
                String orden="";
                System.out.println(request.getParameter("pagina").toString());
                pagina2 = (String) request.getParameter("pagina");
                orden = (String) request.getParameter("orden");
                int paginaSiguiente = Integer.parseInt(pagina2);
                switch(orden){
                    case "Siguiente":
                        paginaSiguiente +=1;
                        break;
                    case "Atras":
                        paginaSiguiente -=1;
                        break;
                       
                    
                }
                
         try{   
                
                System.out.println("valor paginaSiguiente "+paginaSiguiente);
                int pagina = con.GetPages("actividad", "activo=true", 10);
                boolean atras;
                if (paginaSiguiente==1){
                    atras=false;
                }else{

                atras=true;//para sacar el boton de atras
                }
                request.setAttribute("atras", atras );
                request.setAttribute("pagina", paginaSiguiente);
                LinkedList<ActividadPojo> tablaActividad = ActividadDao.mostrarActividadSiguiente(paginaSiguiente);
                request.setAttribute("tabla", tablaActividad);
         }catch(Exception e){
    }
                
                


                getServletContext().getRequestDispatcher("/actividades.jsp")
                        .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
