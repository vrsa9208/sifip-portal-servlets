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
import mx.com.vrsa9208.sifiplibrary.model.Categoria;

/**
 *
 * @author vrsa9208
 */
public interface CategoriaService {
    
    void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    Categoria getById(int id);
}
