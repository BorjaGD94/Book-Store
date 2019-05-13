/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Retailer;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Borja
 */

@Named
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
    
    /**
     *
     * @param a
     * @param u
     */
    public void updateRetailerInfo(Retailer a, User u){
        Retailer retailerFromDatabase = em.getReference(Retailer.class, a.getId());
        
        
        retailerFromDatabase.setName(a.getName());
        retailerFromDatabase.setLastName(a.getLastName());
        retailerFromDatabase.setEmail(a.getEmail());
        retailerFromDatabase.getUser().setUserName(u.getUserName());
        
        em.merge(retailerFromDatabase);
    }
    
}
