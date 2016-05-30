/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.servlets;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.vrsa9208.sifipportal.service.PresupuestoService;
import mx.com.vrsa9208.sifipportal.service.impl.MovimientoServiceImpl;
import mx.com.vrsa9208.sifipportal.service.impl.PresupuestoServiceImpl;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author vrsa9208
 */
public class Presupuesto extends HttpServlet {

    private PresupuestoService service;

    public Presupuesto() {
        this.service = PresupuestoServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Valida la sesión del usuario
        if (!validarUsuario(request)) {
            response.sendRedirect("Login");
        }

        if (request.getParameter("action") == null) {
            index(request, response);
        } else if (request.getParameter("action").equals("agregar")) {
            agregar(request, response);
        } else if (request.getParameter("action").equals("detalles")) {
            detalles(request, response);
        } else if (request.getParameter("action").equals("editar")) {
            editar(request, response);
        } else if (request.getParameter("action").equals("eliminar")) {
            eliminar(request, response);
        } else if (request.getParameter("action").equals("movimientos")) {
            movimientos(request, response);
        } else {
            response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Valida la sesión del usuario
        if (!validarUsuario(request)) {
            response.sendRedirect("Login");
        }

        if (request.getParameter("action") == null) {
            response.sendError(404);
        } else if (request.getParameter("action").equals("agregar")) {
            agregarPost(request, response);
        } else if (request.getParameter("action").equals("editar")) {
            editarPost(request, response);
        } else{
            response.sendError(404);
        }
    }

    private boolean validarUsuario(HttpServletRequest request) {
        //Valida la sesión del usuario
        HttpSession session = request.getSession();
        return (session.getAttribute("usuario") == null) ? false : true;
    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Presupuestos");
        request.setAttribute("menuPresupuestos", true);
        request.setAttribute("page", "/Views/Presupuesto/index.jsp");
        
        HttpSession session = request.getSession();
        mx.com.vrsa9208.sifiplibrary.model.Usuario usuario = (mx.com.vrsa9208.sifiplibrary.model.Usuario) session.getAttribute("usuario");
        request.setAttribute("model", service.getByIdUsuario(usuario.getId()));
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Agregar");
        request.setAttribute("menuPresupuestos", true);
        request.setAttribute("page", "/Views/Presupuesto/agregar.jsp");
        request.setAttribute("model", new mx.com.vrsa9208.sifiplibrary.model.Presupuesto());
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }
    
    private void detalles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Detalles");
        request.setAttribute("menuPresupuestos", true);
        request.setAttribute("page", "/Views/Presupuesto/detalles.jsp");
        request.setAttribute("model", service.getById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Editar");
        request.setAttribute("menuPresupuestos", true);
        request.setAttribute("page", "/Views/Presupuesto/agregar.jsp");
        request.setAttribute("model", service.getById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            service.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("Presupuesto");
        } catch (Exception ex) {
            request.setAttribute("title", "Eliminar");
            request.setAttribute("menuPresupuestos", true);
            request.setAttribute("page", "/Views/Presupuesto/index.jsp");
            
            HttpSession session = request.getSession();
            mx.com.vrsa9208.sifiplibrary.model.Usuario usuario = (mx.com.vrsa9208.sifiplibrary.model.Usuario) session.getAttribute("usuario");
            request.setAttribute("model", service.getByIdUsuario(usuario.getId()));
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
        }
    }
    
    private void movimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Lista Movimientos");
        request.setAttribute("menuPresupuestos", true);
        request.setAttribute("page", "/Views/Presupuesto/movimientos.jsp");
        request.setAttribute("presupuesto", service.getById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("model", MovimientoServiceImpl.getInstance().getByPresupuestoId(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
    }

    private void agregarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            service.add(parsePresupuesto(request));
            response.sendRedirect("Presupuesto");
        } catch (Exception ex) {
            request.setAttribute("title", "Agregar");
            request.setAttribute("menuPresupuestos", true);
            request.setAttribute("page", "/Views/Presupuesto/agregar.jsp");
            request.setAttribute("model", parsePresupuesto(request));
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
        }
    }
    
    private void editarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            mx.com.vrsa9208.sifiplibrary.model.Presupuesto presupuesto = parsePresupuesto(request);
            presupuesto.setId(Integer.parseInt(request.getParameter("id")));
            service.update(presupuesto);
            response.sendRedirect("Presupuesto");
        } catch (Exception ex) {
            request.setAttribute("title", "Editar");
            request.setAttribute("menuPresupuestos", true);
            request.setAttribute("page", "/Views/Presupuesto/agregar.jsp");
            request.setAttribute("model", parsePresupuesto(request));
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher(PageDirectory.LAYOUT).forward(request, response);
        }
    }

    private mx.com.vrsa9208.sifiplibrary.model.Presupuesto parsePresupuesto(HttpServletRequest request) {
        mx.com.vrsa9208.sifiplibrary.model.Presupuesto presupuesto = new mx.com.vrsa9208.sifiplibrary.model.Presupuesto();
        presupuesto.setDescripcion(request.getParameter("descripcion"));
        presupuesto.setFechaInicio(parseCalendar(request.getParameter("fechaInicio")));
        presupuesto.setFechaFin(parseCalendar(request.getParameter("fechaFin")));
        
        HttpSession session = request.getSession();
        mx.com.vrsa9208.sifiplibrary.model.Usuario usuario = (mx.com.vrsa9208.sifiplibrary.model.Usuario) session.getAttribute("usuario");
        presupuesto.setIdUsuario(usuario.getId());
        return presupuesto;
    }

    private GregorianCalendar parseCalendar(String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date date = formatter.parse(fecha);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
