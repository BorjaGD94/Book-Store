 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.domain.PurchaseHistory;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.time.LocalDate;

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
    
    /**
     * The createWithClient method will create a new book in the database, and
     * associate that book with the client that owns it passed as a parameter.
     * 
     * @param b the new book to create in the database
     * @param c the client that owns the book, to whom the book will be added
     */
    public void associateBook(Book b, Client c) {
        //client = clientSvc.findByUsername(loginController.getRemoteUser());
        Book bookFromDatabase = em.getReference(Book.class, b.getId());
        bookFromDatabase.setClient(c);
        c.addBook(bookFromDatabase);
        em.merge(c);
        em.merge(bookFromDatabase);
        
        PurchaseHistory pur = new PurchaseHistory(LocalDate.now());
        pur.setClient(c);
        pur.setBook(bookFromDatabase);
        em.merge(pur);
    }
    
    /**
     *
     * @param a
     * @param u
     */
    public void updateClientInfo(Client a, User u){
        Client clientFromDatabase = em.getReference(Client.class, a.getId());
        
        
        clientFromDatabase.setName(a.getName());
        clientFromDatabase.setLastName(a.getLastName());
        clientFromDatabase.setEmail(a.getEmail());
        clientFromDatabase.getUser().setUserName(u.getUserName());
        
        em.merge(clientFromDatabase);
    }
}
