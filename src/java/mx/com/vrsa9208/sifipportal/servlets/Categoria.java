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
import mx.com.vrsa9208.sifipportal.service.CategoriaService;
import mx.com.vrsa9208.sifipportal.service.impl.CategoriaServiceImpl;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author vrsa9208
 */
public class Categoria extends HttpServlet {
    
    private CategoriaService service;

    public Categoria() {
        super();
        this.service = CategoriaServiceImpl.getInstance();
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
        
        mx.com.vrsa9208.sifiplibrary.model.Usuario usuarioLogeado = (mx.com.vrsa9208.sifiplibrary.model.Usuario) session.getAttribute("usuario");
        if(usuarioLogeado.getId_perfil() != 1){
            //1 => id de perfil tipo administrador
            request.setAttribute("title", "Sin permisos");
            request.setAttribute("menuCategorias", true);
            request.setAttribute("page", PageDirectory.SIN_PERMISOS);
            request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
            return;
        }
        
        String action = request.getParameter("action");

        if (action == null) {
            this.service.list(request, response);
        }
        else if(action.equals("add")){
            this.add(request, response);
        }
        else if(action.equals("edit")){
            this.edit(request, response);
        }
        else if(action.equals("delete")){
            this.service.delete(request, response);
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
        
        mx.com.vrsa9208.sifiplibrary.model.Usuario usuarioLogeado = (mx.com.vrsa9208.sifiplibrary.model.Usuario) session.getAttribute("usuario");
        if(usuarioLogeado.getId_perfil() != 1){
            //1 => id de perfil tipo administrador
            request.setAttribute("title", "Sin permisos");
            request.setAttribute("menuCategorias", true);
            request.setAttribute("page", PageDirectory.SIN_PERMISOS);
            request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
            return;
        }
        
        String action = request.getParameter("action");

        if (action == null) {
            this.service.list(request, response);
        }
        else if(action.equals("add")){
            this.service.add(request, response);
            this.add(request, response);
        }
        else if(action.equals("edit")){
            this.service.edit(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Agregar Categoría");
        request.setAttribute("menuCategorias", true);
        request.setAttribute("page", PageDirectory.AGREGAR_EDITAR_CATEGORIA);
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Editar Categoría");
        request.setAttribute("menuCategorias", true);
        request.setAttribute("page", PageDirectory.AGREGAR_EDITAR_CATEGORIA);
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("categoria", service.getById(id));
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }
}
