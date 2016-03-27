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
import javax.servlet.http.HttpSession;
import mx.com.vrsa9208.sifiplibrary.dao.UsuarioDao;
import mx.com.vrsa9208.sifiplibrary.dao.impl.UsuarioDaoJDBC;
import mx.com.vrsa9208.sifiplibrary.model.Usuario;
import mx.com.vrsa9208.sifipportal.service.UsuarioService;
import mx.com.vrsa9208.sifipportal.util.PageDirectory;

/**
 *
 * @author Administrador
 */
public class UsuarioServiceImpl implements UsuarioService{
    
    private static UsuarioServiceImpl instance;
    private UsuarioDao dao;
    
    private UsuarioServiceImpl(){
        dao = UsuarioDaoJDBC.getInstance();
    }
    
    public static UsuarioServiceImpl getInstance(){
        if(instance == null) instance = new UsuarioServiceImpl();
        return instance;
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Parametros
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String mensajeError = null;
        Usuario usuario = dao.getByEmailAndPassword(email, password);
        //Primero revisa que el usuario exista en el sistema
        if (usuario != null) {
            //Como segundo paso, revisa que el usuario esté activo
            if (usuario.isActivo()) {
                //Una vez logeado, se guarda el usuario en la sesion y se redirige a home
                System.out.println("El usuario " + usuario.getNombre() + " accedio al sistema");
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                response.sendRedirect("Home");
            }
            else{
                mensajeError = "El usuario no se encuentra activo";
            }
        } else {
            mensajeError = "Los datos incorrectos";
        }

        //Si hay mensaje de error, se muesta el error y se redirige a Login
        if (mensajeError != null) {
            request.setAttribute("mensajeError", mensajeError);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.getRequestDispatcher(PageDirectory.LOGIN).forward(request, response);
        }
    }

    @Override
    public void cambiarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Parametros
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");
        
        String mensajeError = null;
        //Se verifica que los passwords coincidan
        if (!newPassword.equals(newPassword2)) {
            mensajeError = "El nuevo password no coincide";
        } else {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            //Luego se verifica que el password actual sea el correcto
            if (dao.getByEmailAndPassword(usuario.getEmail(), currentPassword) == null) {
                mensajeError = "El password actual es incorrecto";
            } else {
                //Se procede a cambiar el password
                if (!dao.updatePassword(usuario.getId(), newPassword)) {
                    mensajeError = "No se ha podido actualizar el password. Intenta de nuevo";
                }
            }
        }
        
        if (mensajeError != null) {
            request.setAttribute("mensajeError", mensajeError);
            request.setAttribute("currentPassword", currentPassword);
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("newPassword2", newPassword2);
        } else {
            request.setAttribute("mensajeSuccess", "Se ha actualizado el password");
        }
    }

    @Override
    public void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensajeError = validarUsuario(request);
        
        //Primero valida que los campos de usuario sean correctos
        if(mensajeError == null){
            Usuario usuario = new Usuario();
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setPrimerApellido(request.getParameter("primerApellido"));
            usuario.setSegundoApellido(request.getParameter("segundoApellido"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setPassword(request.getParameter("password"));
            
            if(dao.add(usuario) == null){
                mensajeError = "No se ha podido registrar el usuario";
            }
        }
        
        if(mensajeError == null){
            request.setAttribute("mensajeSuccess", "Usuario Registrado");
            request.getRequestDispatcher(PageDirectory.LOGIN).forward(request, response);
        }
        else{
            request.setAttribute("nombre", request.getParameter("nombre"));
            request.setAttribute("primerApellido", request.getParameter("primerApellido"));
            request.setAttribute("segundoApellido", request.getParameter("segundoApellido"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("password", request.getParameter("password"));
            request.setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher(PageDirectory.REGISTRAR_USUARIO).forward(request, response);
        }
    }
    
    private String validarUsuario(HttpServletRequest request){
        String mensajeError = null;
        //Parametros
        String nombre = request.getParameter("nombre");
        if(nombre == null || nombre.equals("")){
            mensajeError = "El campo nombre es obligatorio";
            return mensajeError;
        }
        
        String primerApellido = request.getParameter("primerApellido");
        if(primerApellido == null || primerApellido.equals("")){
            mensajeError = "El campo primer apellido es obligatorio";
            return mensajeError;
        }
        
        String email = request.getParameter("email");
        if(email == null || email.equals("")){
            mensajeError = "El campo e-mail es obligatorio";
            return mensajeError;
        }
        
        String password = request.getParameter("password");
        if(password == null || password.equals("")){
            mensajeError = "El campo password es obligatorio";
            return mensajeError;
        }
        
        return mensajeError;
    }
}
