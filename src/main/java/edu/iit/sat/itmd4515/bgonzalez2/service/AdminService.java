 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Administrator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Borja
 */

@Named
@Stateless
public class AdminService extends AbstractService<Administrator> {

    private static final Logger LOG = Logger.getLogger(AdminService.class.getName());
    
    /**
     *
     */
    public AdminService() {
        super(Administrator.class);
    }
    
    /**
     *
     * @param a
     * @return
     */
    public Administrator createAdministrator(Administrator a) {
        em.persist(a);
        // in the middle of the EJB method, if we need a commit - we need to manage that with the EM
        em.flush();
        return a;
    } // at the end of an EJB method, we can expect the persistence context to be flushed (written to the database)
    
    /**
     *
     * @param id
     * @return
     */
    public Administrator find(Long id){
        return em.find(Administrator.class, id);
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Administrator findByName(String name){
        return em.createNamedQuery("Administrator.findByName", Administrator.class).setParameter("name", name).getSingleResult();
    }
    
    /**
     *
     * @return
     */
    public List<Administrator> findAll() {
        return em.createNamedQuery("Administrator.findAll", Administrator.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Administrator findByUsername(String username) {
        LOG.info("The username is: " + username);
        return em.createNamedQuery("Administrator.findByUsername", Administrator.class).setParameter("username", username).getSingleResult();
    }
}
