/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Retailer;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import edu.iit.sat.itmd4515.bgonzalez2.service.BookService;
import edu.iit.sat.itmd4515.bgonzalez2.service.RetailerService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Borja
 */

@Named
@RequestScoped
public class RetailerController {
private static final Logger LOG = Logger.getLogger(RetailerController.class.getName());
    
    @EJB 
    private RetailerService retailerSvc;
    
    @EJB
    private BookService bookSvc;
    
    @Inject 
    LoginController loginController;
    
    private Retailer retailer;
    private Book book;
 
    /**
     *
     */
    public RetailerController() {
    }
    
    @PostConstruct
    private void postContsruct(){
        LOG.info("RetailerController is firing postConstruct()");
        retailer = retailerSvc.findByUsername(loginController.getRemoteUser());
        book = new Book();
    }
    
    // prepare for the action methods

    /**
     *
     * @param b
     * @return
     */
    public String prepareViewBook(Book b) {
        LOG.info("Inside prepareViewBook with book " + b.toString());
        this.book = b;
        return "/retailer/viewBook.xhtml";
    }


    /**
     *
     * @return
     */
    public String prepareCreateBook() {
        LOG.info("Inside prepareCreateBook");
        this.book = new Book();
        
        return "/retailer/editBook.xhtml";

    }

    //
    // action methods

    /**
     *
     * @return
     */
    public String doSaveBook() {
        // when complete, this method will invoke service layer
        // this method will need to be smart enough to know when to edit, and when to create
        LOG.info("Inside doSaveBook with book " + book.toString());
        
        if(this.book.getId() != null){
            LOG.info("Preparing to call an update in the service layer with " + this.book.toString());
            bookSvc.editBookWithNoChangeToRetailer(book);
        } else {
            LOG.info("Preparing to create in the service layer with " + this.book.toString());
            bookSvc.createWithRetailer(book, retailer);
        }
        
        return "/retailer/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     *
     * @param a
     * @param u
     * @return
     */
    public String updateUser(Retailer a, User u){
        LOG.info("Inside update User with retailer " + a.toString());
        LOG.info("Inside update User with username: " + u.toString());
        retailerSvc.updateRetailerInfo(a, u);
        
        return "/admin/welcome.xhtml?faces-redirect=true";
    }


    /**
     *
     * @param b
     * @return
     */
    public String doDeleteBook(Book b) {
        LOG.info("Inside doDeleteBook with book " + b.toString());
        bookSvc.remove(b);
        return "/retailer/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     * Get the value of retailer
     *
     * @return the value of retailer
     */
    public Retailer getRetailer() {
        return retailer;
    }

    /**
     * Set the value of client
     *
     * @param retailer new value of retailer
     */
    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    /**
     * Get the value of book
     * 
     * @return
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set the value of book
     * 
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
    }
      
}
