/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author vrsa9208
 */
public class Perfil extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Valida la sesión del usuario
        HttpSession session = request.getSession();
        if(session.getAttribute("usuario") == null){
            response.sendRedirect("Login");
            return;
        }
        
        String accion = request.getParameter("action");
        if(accion == null){
            this.getPerfil(request, response);
        }
        else if(accion == "cambiarPassword"){
            System.out.println("Cambiando password");
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
    
    private void getPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("title", "Mi perfil");
        request.setAttribute("menuMiPerfil", true);
        request.setAttribute("page", PageDirectory.MI_PERFIL_PAGE);
        request.getRequestDispatcher(PageDirectory.LAYOUT_PAGE).forward(request, response);
    }

}
