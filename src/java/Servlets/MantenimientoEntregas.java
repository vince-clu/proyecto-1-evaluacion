/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Daos.DocumentoDao;
import Daos.EntregaDao;
import MySQL.MySql;
import Pojos.DocumentoPojo;
import Pojos.EntregaPojo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vesprada
 */
@WebServlet(name = "MantenimientoEntregas", urlPatterns = {"/MantenimientoEntregas"})
public class MantenimientoEntregas extends HttpServlet {

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
      
        
        int id_documento= Integer.parseInt(request.getParameter("idDocumentoOculta"));

        int id_actividad=Integer.parseInt(request.getParameter("idActividadOculta"));

        EntregaPojo pojo= new EntregaPojo();
        pojo.setId_actividad(id_actividad);
        pojo.setId_documento(id_documento);
        HttpSession sesion = request.getSession();
        int id_usuario;
        id_usuario=(int)sesion.getAttribute("id");
        pojo.setId_usuario(id_usuario);
        LinkedList<EntregaPojo> tablaEntrega= EntregaDao.entregarymostrarEntrega(pojo);
        
        request.setAttribute("tabla", tablaEntrega);
        getServletContext().getRequestDispatcher("/entregas.jsp")
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
