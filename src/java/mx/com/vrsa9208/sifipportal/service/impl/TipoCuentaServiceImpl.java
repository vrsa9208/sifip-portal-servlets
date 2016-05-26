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
import mx.com.vrsa9208.sifiplibrary.dao.TipoCuentaDao;
import mx.com.vrsa9208.sifiplibrary.dao.impl.TipoCuentaDaoJDBC;
import mx.com.vrsa9208.sifiplibrary.model.TipoCuenta;
import mx.com.vrsa9208.sifipportal.service.TipoCuentaService;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author vrsa9208
 */
public class TipoCuentaServiceImpl implements TipoCuentaService{
    
    private static TipoCuentaServiceImpl instance;
    private TipoCuentaDao dao;
    
    private TipoCuentaServiceImpl(){
        this.dao = TipoCuentaDaoJDBC.getInstance();
    }
    
    public static TipoCuentaServiceImpl getInstance(){
        if(instance == null) instance = new TipoCuentaServiceImpl();
        return instance;
    }

    @Override
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Tipos de Cuenta");
        request.setAttribute("menuTiposDeCuenta", true);
        request.setAttribute("page", PageDirectory.LISTA_TIPO_CUENTA);
        request.setAttribute("listaTiposDeCuenta", dao.get());
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }

    @Override
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parametros
        String descripcion = request.getParameter("descripcion");
        TipoCuenta tipoCuenta = new TipoCuenta();
        tipoCuenta.setDescripcion(descripcion);
        if(dao.add(tipoCuenta) == null){
            request.setAttribute("mensajeError", "Error al agregar el tipo de cuenta");
        }
        else{
            request.setAttribute("mensajeSuccess", "Tipo de Cuenta agregada con exito");
        }
    }

    @Override
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parametros
        String descripcion = request.getParameter("descripcion");
        int id = Integer.parseInt(request.getParameter("id"));
        TipoCuenta tipoCuenta = dao.getById(id);
        tipoCuenta.setDescripcion(descripcion);
        if(dao.update(tipoCuenta) == null){
            request.setAttribute("mensajeError", "Error al actualizar el tipo de cuenta");
        }
        else{
            request.setAttribute("mensajeSuccess", "Tipo de Cuenta actualizada con exito");
        }
        request.setAttribute("title", "Editar Tipo de Cuenta");
        request.setAttribute("menuTiposDeCuenta", true);
        request.setAttribute("page", PageDirectory.AGREGAR_EDITAR_TIPO_CUENTA);
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.delete(id);
        response.sendRedirect("TipoDeCuenta");
    }

    @Override
    public TipoCuenta getById(int id) {
        return dao.getById(id);
    }
    
}
