/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.vrsa9208.sifipportal.service;

import java.util.List;
import mx.com.vrsa9208.sifiplibrary.model.Presupuesto;

/**
 *
 * @author vrsa9208
 */
public interface PresupuestoService {
    Presupuesto add(Presupuesto presupuesto);
    Presupuesto update(Presupuesto presupuesto);
    boolean delete(int id);
    List<Presupuesto> get();
    Presupuesto getById(int id); 
    List<Presupuesto> getByIdUsuario(int id);
}
