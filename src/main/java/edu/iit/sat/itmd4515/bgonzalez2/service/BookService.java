/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.domain.PurchaseHistory;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Borja
 */
@Named
@Stateless
public class BookService extends AbstractService<Book> {

    private static final Logger LOG = Logger.getLogger(BookService.class.getName());
    

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    public BookService() {
        super(Book.class);
    }

    /**
     * Find a Book
     *
     * @param id The PK of book
     * @return return a book object or null
     */
    public Book find(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }
    
    // example of an update from a user form - can not change client
    public void editBookWithNoChangeToClient(Book bookFromClientForm){
        
        // Get the book from the database and set the new values
        Book bookFromDatabase = em.getReference(Book.class, bookFromClientForm.getId());
        
        bookFromDatabase.setTitle(bookFromClientForm.getTitle());
        bookFromDatabase.setAuthor(bookFromClientForm.getAuthor());
        bookFromDatabase.setGenre(bookFromClientForm.getGenre());
        bookFromDatabase.setYearPublished(bookFromClientForm.getYearPublished());
        
        // Update the book in the database
        em.merge(bookFromDatabase);
        
    }
    
    // Accepts the currently logged user(client/retailer)
    public void createWithClient(Book b, Client c) {
        // bring the front-end client into persistence context
        c = em.getReference(Client.class, c.getId());

        // create the new book from user input
        super.create(b);
        // making sure the new book has its db generated id
        em.flush();

        // settting both sides of the relationship now
        c.addBook(b);
        b.setClient(c);
        // merging the client, because this entity is the owning side of the relationship
        // it controls the database updates and creates the FK
        em.merge(c);
    }
    
    public void createAndAddClient(Book bookFromAdminForm){
        
        Client c = bookFromAdminForm.getClient();
        
        bookFromAdminForm.setClient(null);      
        em.persist(bookFromAdminForm);
        em.flush();
        
        Book bookFromDatabase = em.getReference(Book.class, bookFromAdminForm.getId());
        bookFromAdminForm.setClient(c);
        c.addBook(bookFromDatabase);
        em.merge(c);
    }

    @Override
    public void update(Book bookFromAdminForm) {
        // Get the book from the database and set the new values
        Book bookFromDatabase = em.getReference(Book.class, bookFromAdminForm.getId());
        
        bookFromDatabase.setTitle(bookFromAdminForm.getTitle());
        bookFromDatabase.setAuthor(bookFromAdminForm.getAuthor());
        bookFromDatabase.setGenre(bookFromAdminForm.getGenre());
        bookFromDatabase.setYearPublished(bookFromAdminForm.getYearPublished());
        
        // the admin form might contain client(s) that are not in the database
        // if so, add them to the db
        Client c = bookFromAdminForm.getClient();
        if (! bookFromDatabase.getClient().equals(c)) {
            c.addBook(bookFromDatabase);
            bookFromDatabase.setClient(c);
        }
        em.merge(c);
        
        em.merge(bookFromDatabase);
    }
    
    
    // This method will accept a book to delete from any form - user or admin
    @Override
    public void remove(Book bookFromUserForm) {
        Book bookFromDatabase = em.getReference(Book.class, bookFromUserForm.getId());
        LOG.info("Hello: " + bookFromUserForm.toString());
        
        PurchaseHistory pur = bookFromDatabase.getPurchase();
        if(bookFromDatabase.getPurchase() == null){
            Client cl = bookFromDatabase.getClient();
            cl.removeBook(bookFromDatabase);
            em.merge(cl);

            em.remove(bookFromDatabase);
        }
        else {
            pur.setClient(null);
            pur.getClient().getPurchases().remove(pur);
            em.remove(pur);
            
            Client cl = bookFromDatabase.getClient();
            cl.removeBook(bookFromDatabase);
            em.merge(cl);

            em.remove(bookFromDatabase);
        }
        
    }
    
    
    

}
