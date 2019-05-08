/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Retailer;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Borja
 */

@Stateless
public class RetailerService extends AbstractService<Retailer> {

    /**
     *
     */
    public RetailerService() {
        super(Retailer.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Retailer> findAll() {
        return em.createNamedQuery("Retailer.findAll", entityClass).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Retailer findByUsername(String username) {
        return em.createNamedQuery("Retailer.findByUsername", Retailer.class).setParameter("username", username).getSingleResult();
    }
    
}
