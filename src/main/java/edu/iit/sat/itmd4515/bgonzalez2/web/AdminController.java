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
public class AdminController {
    
    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());
    
    @EJB private BookService bookSvc;
    
    private Book book;
    
    public AdminController() {
    }  
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside AdminController.postConstruct()");
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
    
    // prepare for the action methods
    public String prepareViewBook(Book b) {
        LOG.info("Inside prepareViewPet with pet " + b.toString());
        this.book = b;
        return "/admin/viewBook.xhtml";
    }

    public String prepareEditBook(Book b) {
        LOG.info("Inside prepareEditBook with book " + b.toString());
        this.book = b;
        return "/admin/editBook.xhtml";
    }

    public String prepareCreateBook() {
        LOG.info("Inside prepareCreateBook");
        this.book = new Book();
        
        return "/admin/editBook.xhtml";

    }

    //
    // action methods
    public String doSaveBook() {
        // when complete, this method will invoke service layer
        // this method will need to be smart enough to know when to edit, and when to create
        LOG.info("Inside doSaveBook with book " + book.toString());
        
        if(this.book.getId() != null){
            LOG.info("Preparing to call an update in the service layer with " + this.book.toString());
            //bookSvc.editBookWithNoChangeToClient(book);
            bookSvc.update(book);
        } else {
            LOG.info("Preparing to create in the service layer with " + this.book.toString());
            bookSvc.createAndAddClient(book);
        }
        
        // option 1 = we could force a refresh of the owner to refresh the collections
        //client = clientSvc.findByUsername(loginController.getRemoteUser());
        
        // option 2 = force a faces-redirect
        return "/admin/welcome.xhtml?faces-redirect=true";
    }

    public String doDeleteBook(Book b) {
        LOG.info("Inside doDeleteBook with book " + b.toString());
        bookSvc.remove(b);
        return "/admin/welcome.xhtml";
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
