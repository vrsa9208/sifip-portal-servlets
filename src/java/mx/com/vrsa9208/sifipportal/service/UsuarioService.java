/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
public interface UsuarioService {
    
    void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void cambiarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
}
