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
import mx.com.vrsa9208.sifiplibrary.model.Usuario;
import mx.com.vrsa9208.sifipportal.service.UsuarioService;
import mx.com.vrsa9208.sifipportal.service.impl.UsuarioServiceImpl;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author Administrador
 */
public class Login extends HttpServlet {

    private UsuarioService service;
    
    public Login(){
        super();
        this.service = UsuarioServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if(usuario == null){
            request.getRequestDispatcher(PageDirectory.LOGIN).forward(request, response);
        }
        else{
            response.sendRedirect("Home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        service.login(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para controlar el login en el sistema";
    }// </editor-fold>

}
