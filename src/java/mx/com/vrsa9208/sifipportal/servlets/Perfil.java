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
import mx.com.vrsa9208.sifipportal.service.PerfilService;
import mx.com.vrsa9208.sifipportal.service.UsuarioService;
import mx.com.vrsa9208.sifipportal.service.impl.PerfilServiceImpl;
import mx.com.vrsa9208.sifipportal.service.impl.UsuarioServiceImpl;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author vrsa9208
 */
public class Perfil extends HttpServlet {

    private UsuarioService service;
    
    public Perfil(){
        super();
        this.service = UsuarioServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Valida la sesión del usuario
        HttpSession session = request.getSession();
        if (session.getAttribute("usuario") == null) {
            response.sendRedirect("Login");
            return;
        }

        String accion = request.getParameter("action");
        if (accion == null) {
            this.getPerfil(request, response);
        } else if (accion.equals("cambiarPassword")) {
            this.cambiarPassword(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Valida la sesión del usuario
        HttpSession session = request.getSession();
        if (session.getAttribute("usuario") == null) {
            response.sendRedirect("Login");
            return;
        }

        String accion = request.getParameter("action");
        if (accion == null) {
            response.sendRedirect("Login");
        } else if (accion.equals("cambiarPassword")) {
            service.cambiarPassword(request, response);
            this.cambiarPassword(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void getPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PerfilService perfilService = PerfilServiceImpl.getInstance();
        HttpSession session = request.getSession();
        mx.com.vrsa9208.sifiplibrary.model.Usuario usuarioLogeado = (mx.com.vrsa9208.sifiplibrary.model.Usuario) session.getAttribute("usuario");
        int idUsuarioLogeado = usuarioLogeado.getId_perfil();
        
        request.setAttribute("title", "Mi perfil");
        request.setAttribute("menuMiPerfil", true);
        request.setAttribute("page", PageDirectory.MI_PERFIL);
        request.setAttribute("perfilTexto", perfilService.getById(idUsuarioLogeado).getDescripcion());
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }

    private void cambiarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Cambiar Password");
        request.setAttribute("menuMiPerfil", true);
        request.setAttribute("page", PageDirectory.CAMBIAR_PASSWORD);
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }
}
