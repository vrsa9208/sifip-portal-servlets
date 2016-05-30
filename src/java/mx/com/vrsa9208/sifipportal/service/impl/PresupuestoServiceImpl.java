/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service.impl;

import java.util.List;
import mx.com.vrsa9208.sifiplibrary.dao.PresupuestoDao;
import mx.com.vrsa9208.sifiplibrary.dao.impl.PresupuestoDaoJDBC;
import mx.com.vrsa9208.sifiplibrary.model.Presupuesto;
import mx.com.vrsa9208.sifipportal.service.PresupuestoService;

/**
 *
 * @author vrsa9208
 */
public class PresupuestoServiceImpl implements PresupuestoService{

    private static PresupuestoServiceImpl instance;
    private PresupuestoDao dao;
    
    private PresupuestoServiceImpl(){
        this.dao = PresupuestoDaoJDBC.getInstance();
    }
    
    public static PresupuestoServiceImpl getInstance(){
        if(instance == null) instance = new PresupuestoServiceImpl();
        return instance;
    }
    
    @Override
    public Presupuesto add(Presupuesto presupuesto) {
        return this.dao.add(presupuesto);
    }

    @Override
    public Presupuesto update(Presupuesto presupuesto) {
        return this.dao.update(presupuesto);
    }

    @Override
    public boolean delete(int id) {
        return this.dao.delete(id);
    }

    @Override
    public List<Presupuesto> get() {
        return this.dao.get();
    }

    @Override
    public Presupuesto getById(int id) {
        return this.dao.getById(id);
    }

    @Override
    public List<Presupuesto> getByIdUsuario(int id) {
        return this.dao.getByIdUsuario(id);
    }
    
}
