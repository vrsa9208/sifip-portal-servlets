/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service.impl;

import java.util.List;
import mx.com.vrsa9208.sifiplibrary.dao.MovimientoDao;
import mx.com.vrsa9208.sifiplibrary.dao.impl.MovimientoDaoJDBC;
import mx.com.vrsa9208.sifiplibrary.model.Movimiento;
import mx.com.vrsa9208.sifipportal.service.MovimientoService;

/**
 *
 * @author vrsa9208
 */
public class MovimientoServiceImpl implements MovimientoService{
    
    private static MovimientoServiceImpl instance;
    private MovimientoDao dao;
    
    private MovimientoServiceImpl(){
        this.dao = new MovimientoDaoJDBC();
    }
    
    public static MovimientoServiceImpl getInstance(){
        if(instance == null) instance = new MovimientoServiceImpl();
        return instance;
    }

    @Override
    public Movimiento add(Movimiento movimiento) {
        return dao.add(movimiento);
    }

    @Override
    public Movimiento update(Movimiento movimiento) {
        return dao.update(movimiento);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public List<Movimiento> get() {
        return dao.get();
    }

    @Override
    public Movimiento getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Movimiento> getByPresupuestoId(int id) {
        return dao.getByPresupuestoId(id);
    }
    
}
