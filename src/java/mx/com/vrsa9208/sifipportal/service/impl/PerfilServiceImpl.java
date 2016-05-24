/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service.impl;

import java.util.List;
import mx.com.vrsa9208.sifiplibrary.dao.PerfilDao;
import mx.com.vrsa9208.sifiplibrary.dao.impl.PerfilDaoJDBC;
import mx.com.vrsa9208.sifiplibrary.model.Perfil;
import mx.com.vrsa9208.sifipportal.service.PerfilService;

/**
 *
 * @author vrsa9208
 */
public class PerfilServiceImpl implements PerfilService{
    
    private static PerfilServiceImpl instance;
    private PerfilDao perfilDao;
    
    private PerfilServiceImpl(){
        perfilDao = PerfilDaoJDBC.getInstance();
    }
    
    public static PerfilServiceImpl getInstance(){
        if(instance == null) instance = new PerfilServiceImpl();
        return instance;
    }

    @Override
    public Perfil add(Perfil perfil) {
        return perfilDao.add(perfil);
    }

    @Override
    public Perfil update(Perfil perfil) {
        return perfilDao.update(perfil);
    }

    @Override
    public boolean delete(int id) {
        return perfilDao.delete(id);
    }

    @Override
    public List<Perfil> get() {
        return perfilDao.get();
    }

    @Override
    public Perfil getById(int id) {
        return perfilDao.getById(id);
    }
}
