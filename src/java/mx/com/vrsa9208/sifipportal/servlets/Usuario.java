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
import mx.com.vrsa9208.sifipportal.service.UsuarioService;
import mx.com.vrsa9208.sifipportal.service.impl.UsuarioServiceImpl;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author vrsa9208
 */
public class Usuario extends HttpServlet {
    
    private UsuarioService service;
    
    public Usuario(){
        super();
        service = UsuarioServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action == null){
            response.sendRedirect("Login");
        }
        else if(action.equals("register")){
            this.registrarUsuario(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action == null){
            response.sendRedirect("Login");
        }
        else if(action.equals("register")){
            this.service.registrarUsuario(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher(PageDirectory.REGISTRAR_USUARIO).forward(request, response);
    }

}
