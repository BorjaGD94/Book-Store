 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
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
public class ClientService extends AbstractService<Client> {

    private static final Logger LOG = Logger.getLogger(ClientService.class.getName());
    
    /**
     *
     */
    public ClientService() {
        super(Client.class);
    }
    
    /**
     *
     * @param c
     * @return
     */
    public Client createClient(Client c) {
        em.persist(c);
        // in the middle of the EJB method, if we need a commit - we need to manage that with the EM
        em.flush();
        return c;
    } // at the end of an EJB method, we can expect the persistence context to be flushed (written to the database)
    
    /**
     *
     * @param id
     * @return
     */
    public Client find(Long id){
        return em.find(Client.class, id);
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Client findByName(String name){
        return em.createNamedQuery("Client.findByName", Client.class).setParameter("name", name).getSingleResult();
    }
    
    /**
     *
     * @return
     */
    public List<Client> findAll() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Client findByUsername(String username) {
        LOG.info("The username is: " + username);
        return em.createNamedQuery("Client.findByUsername", Client.class).setParameter("username", username).getSingleResult();
    }
}
