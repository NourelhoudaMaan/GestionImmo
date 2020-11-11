/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Materielsmar;

/**
 *
 * @author HP
 */
@Stateless
public class MaterielsmarFacade extends AbstractFacade<Materielsmar> {

    @PersistenceContext(unitName = "GestionPU2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterielsmarFacade() {
        super(Materielsmar.class);
    }
    
}
