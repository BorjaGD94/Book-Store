/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import java.util.List;
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
public class BookService extends AbstractService<Book>{

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
    public Book find(Long id){
        return em.find(Book.class, id);
    }
    
    public List<Book> findAll(){
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }
    
        public void createAndAddClient(Book b, Client c){
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
    
}
