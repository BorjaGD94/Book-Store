/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.service.BookService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Borja
 */

@Named
@RequestScoped
public class BookController {
    
    private static final Logger LOG = Logger.getLogger(BookController.class.getName());
    
    @EJB private BookService bookSvc;
    
    private Book book;
    
    public BookController() {
    }  
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside BookController.postConstruct()");
        book = new Book();
    }
    
    public List<Book> getAllBooks(){
        return bookSvc.findAll();
    }
    
    public String formatClientAsString(Book b){
        Client c = b.getClient();
        if (c == null){
            return " ";
        }else{
            String name = c.getName();
            return name;
        }
    }
    
    // action methods
    public String executeSaveBook(){
        LOG.info("Inside BookController.executeSaveBook() " + book.toString());
        bookSvc.create(book);
        return "/admin/bookok.xhtml";
    }
    
        /**
     * Get the value of book
     *
     * @return the value of book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set the value of book
     *
     * @param book new value of book
     */
    public void setBook(Book book) {
        this.book = book;
    }
    
}
