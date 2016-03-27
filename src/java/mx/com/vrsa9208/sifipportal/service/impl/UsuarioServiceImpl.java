/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service.impl;

import mx.com.vrsa9208.sifiplibrary.dao.UsuarioDao;
import mx.com.vrsa9208.sifiplibrary.dao.impl.UsuarioDaoJDBC;
import mx.com.vrsa9208.sifiplibrary.model.Usuario;
import mx.com.vrsa9208.sifipportal.service.UsuarioService;

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
    public Usuario login(String email, String password) {
        return dao.getByEmailAndPassword(email, password);
    }

    @Override
    public boolean cambiarPassword(String password, int idUsuario) {
        return dao.updatePassword(idUsuario, password);
    }
    
}
