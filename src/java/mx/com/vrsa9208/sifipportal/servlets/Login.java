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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if(usuario == null){
            request.getRequestDispatcher(PageDirectory.LOGIN_PAGE).forward(request, response);
        }
        else{
            response.sendRedirect("Home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service = UsuarioServiceImpl.getInstance();
        //Parametros
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String mensajeError = null;
        Usuario usuario = service.login(email, password);
        if (usuario != null) {
            if (usuario.isActivo()) {
                System.out.println("El usuario " + usuario.getNombre() + " accedio al sistema");
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                response.sendRedirect("Home");
            }
            else{
                mensajeError = "El usuario no se encuentra activo";
            }
        } else {
            mensajeError = "Datos incorrectos";
        }

        if (mensajeError != null) {
            request.setAttribute("mensajeError", mensajeError);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.getRequestDispatcher(PageDirectory.LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para controlar el login en el sistema";
    }// </editor-fold>

}
