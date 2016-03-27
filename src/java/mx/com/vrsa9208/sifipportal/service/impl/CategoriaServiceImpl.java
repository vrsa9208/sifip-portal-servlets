/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.vrsa9208.sifiplibrary.dao.CategoriaDao;
import mx.com.vrsa9208.sifiplibrary.dao.impl.CategoriaDaoJDBC;
import mx.com.vrsa9208.sifiplibrary.model.Categoria;
import mx.com.vrsa9208.sifipportal.service.CategoriaService;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author vrsa9208
 */
public class CategoriaServiceImpl implements CategoriaService{
    
    private static CategoriaServiceImpl instance;
    private CategoriaDao dao;
    
    private CategoriaServiceImpl(){
        this.dao = CategoriaDaoJDBC.getInstance();
    }
    
    public static CategoriaServiceImpl getInstance(){
        if(instance == null) instance = new CategoriaServiceImpl();
        return instance;
    }

    @Override
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Categorías");
        request.setAttribute("menuCategorias", true);
        request.setAttribute("page", PageDirectory.LISTA_CATEGORIAS);
        request.setAttribute("listaCategorias", dao.get());
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }

    @Override
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parametros
        String descripcion = request.getParameter("descripcion");
        Categoria categoria = new Categoria();
        categoria.setDescripcion(descripcion);
        if(dao.add(categoria) == null){
            request.setAttribute("mensajeError", "Error al agregar la categoría");
        }
        else{
            request.setAttribute("mensajeSuccess", "Categoría agregada con exito");
        }
    }

    @Override
    public Categoria getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parametros
        String descripcion = request.getParameter("descripcion");
        int id = Integer.parseInt(request.getParameter("id"));
        Categoria categoria = dao.getById(id);
        categoria.setDescripcion(descripcion);
        if(dao.update(categoria) == null){
            request.setAttribute("mensajeError", "Error al actualizar la categoría");
        }
        else{
            request.setAttribute("mensajeSuccess", "Categoría actualizada con exito");
        }
        request.setAttribute("title", "Editar Categoría");
        request.setAttribute("menuCategorias", true);
        request.setAttribute("page", PageDirectory.AGREGAR_EDITAR_CATEGORIA);
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.delete(id);
        response.sendRedirect("Categoria");
    }
    
}
