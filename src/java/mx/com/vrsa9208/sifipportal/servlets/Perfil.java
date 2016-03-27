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
 * @author vrsa9208
 */
public class Perfil extends HttpServlet {

    UsuarioService service;

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
            this.cambiarPasswordPost(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void getPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Mi perfil");
        request.setAttribute("menuMiPerfil", true);
        request.setAttribute("page", PageDirectory.MI_PERFIL_PAGE);
        request.getRequestDispatcher(PageDirectory.LAYOUT_PAGE).forward(request, response);
    }

    private void cambiarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Cambiar Password");
        request.setAttribute("menuMiPerfil", true);
        request.setAttribute("page", PageDirectory.CAMBIAR_PASSWORD_PAGE);
        request.getRequestDispatcher(PageDirectory.LAYOUT_PAGE).forward(request, response);
    }

    private void cambiarPasswordPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        service = UsuarioServiceImpl.getInstance();
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");
        String mensajeError = null;
        if (!newPassword.equals(newPassword2)) {
            mensajeError = "El nuevo password no coincide";
        } else {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (service.login(usuario.getEmail(), currentPassword) == null) {
                mensajeError = "El password actual es incorrecto";
            } else {
                if (!service.cambiarPassword(newPassword, usuario.getId())) {
                    mensajeError = "No se ha podido actualizar el password. Intenta de nuevo";
                }
            }
        }
        
        if (mensajeError != null) {
            request.setAttribute("mensajeError", mensajeError);
            request.setAttribute("currentPassword", currentPassword);
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("newPassword2", newPassword2);
            this.cambiarPassword(request, response);
        } else {
            request.setAttribute("mensajeSuccess", "Se ha actualizado el password");
            this.cambiarPassword(request, response);
        }
    }

}
