/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service;

import mx.com.vrsa9208.sifiplibrary.model.Usuario;

/**
 *
 * @author Administrador
 */
public interface UsuarioService {
    
    Usuario login(String email, String password);
    boolean cambiarPassword(String password, int idUsuario);
}
