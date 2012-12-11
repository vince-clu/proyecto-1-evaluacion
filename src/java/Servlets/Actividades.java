 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Daos.ActividadDao;
import Daos.UsuarioDao;
import MySQL.MySql;
import Pojos.UsuarioPojo;
import Pojos.ActividadPojo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

/**
 *
 * @author vesprada
 */
public class Actividades extends HttpServlet {

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


        try {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            HttpSession sesion = request.getSession();
            String usuario = request.getParameter("user");
            String password = request.getParameter("pass");
            UsuarioDao dao = new UsuarioDao();
            UsuarioPojo pojo = new UsuarioPojo();
            pojo.setUser(usuario);
            pojo.setPass(password);
            pojo = dao.autentificar(pojo);

            if (pojo.getId() != 0) {

                sesion.setAttribute("id", pojo.getId());
                sesion.setAttribute("nombre", pojo.getNombre());
                sesion.setAttribute("ape1", pojo.getApe1());


                sesion.setAttribute("id_tipo_usuario", pojo.getId_tipo_usuario());

                //*****//

                MySql con = new MySql();
                ArrayList<String> condiciones = new ArrayList();
                condiciones.add("activo=true");

                int pagina = con.GetPages("actividad", "activo=true", 10); // +1;

                System.out.println("Numero de paginas: " + (pagina ));


                boolean atras=false;

                //*****//
                LinkedList<ActividadPojo> tablaActividad = ActividadDao.mostrarActividad();
                request.setAttribute("pagina", pagina);
                request.setAttribute("tabla", tablaActividad);
                request.setAttribute("atras", atras);
                getServletContext().getRequestDispatcher("/actividades.jsp")
                        .forward(request, response);
            } else {

                response.sendRedirect("index.jsp");


            }

        } catch (Exception e) {
        }




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
