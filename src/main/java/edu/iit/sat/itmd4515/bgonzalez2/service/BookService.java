/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
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
public class BookService {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    public BookService() {
    }
    
    public void create(Book c){
        em.persist(c);
    }
    
    public void update(Book c){
        em.merge(c);
    }
    
    public void remove(Book c){
        em.remove(em.merge(c));
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
    
}
