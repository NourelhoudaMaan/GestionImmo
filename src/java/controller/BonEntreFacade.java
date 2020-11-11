/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.BonEntre;

/**
 *
 * @author HP
 */
@Stateless
public class BonEntreFacade extends AbstractFacade<BonEntre> {

    @PersistenceContext(unitName = "GestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BonEntreFacade() {
        super(BonEntre.class);
    }
    
}
