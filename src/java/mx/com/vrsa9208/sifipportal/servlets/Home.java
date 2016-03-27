/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author Administrador
 */
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Valida la sesión del usuario
        HttpSession session = request.getSession();
        if (session.getAttribute("usuario") == null) {
            response.sendRedirect("Login");
            return;
        }

        String action = request.getParameter("action");

        if (action == null) {
            request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
        }
        else if(action.equals("mostrarCatalogos")){
            this.mostrarCatalogos(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void mostrarCatalogos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("title", "Catálogos");
        request.setAttribute("menuCatalogos", true);
        //request.setAttribute("page", PageDirectory.CAMBIAR_PASSWORD_PAGE);
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }
}
