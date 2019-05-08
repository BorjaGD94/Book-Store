 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.service.BookService;
import edu.iit.sat.itmd4515.bgonzalez2.service.ClientService;
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
public class ClientController {

    private static final Logger LOG = Logger.getLogger(ClientController.class.getName());
    
    @EJB 
    private ClientService clientSvc;
    
    @EJB
    private BookService bookSvc;
    
    @Inject 
    LoginController loginController;
    
    private Client client;
    private Book book;
 
    /**
     *
     */
    public ClientController() {
    }
    
    @PostConstruct
    private void postContsruct(){
        LOG.info("ClientController is firing postConstruct()");
        client = clientSvc.findByUsername(loginController.getRemoteUser());
        book = new Book();
    }
    
    // prepare for the action methods

    /**
     *
     * @param b
     * @return
     */
    public String prepareViewBook(Book b) {
        LOG.info("Inside prepareViewPet with pet " + b.toString());
        this.book = b;
        return "/client/viewBook.xhtml";
    }

    /**
     *
     * @param b
     * @return
     */
    public String prepareEditBook(Book b) {
        LOG.info("Inside prepareEditBook with book " + b.toString());
        this.book = b;
        return "/client/editBook.xhtml";
    }

    /**
     *
     * @return
     */
    public String prepareCreateBook() {
        LOG.info("Inside prepareCreateBook");
        this.book = new Book();
        
        return "/client/editBook.xhtml";

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
            bookSvc.editBookWithNoChangeToClient(book);
        } else {
            LOG.info("Preparing to create in the service layer with " + this.book.toString());
            bookSvc.createWithClient(book, client);
        }
        
        // option 1 = we could force a refresh of the owner to refresh the collections
        //client = clientSvc.findByUsername(loginController.getRemoteUser());
        
        // option 2 = force a faces-redirect
        return "/client/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @param b
     * @return
     */
    public String doDeleteBook(Book b) {
        LOG.info("Inside doDeleteBook with book " + b.toString());
        bookSvc.remove(b);
        return "/client/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     * Get the value of client
     *
     * @return the value of client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Set the value of client
     *
     * @param client new value of client
     */
    public void setClient(Client client) {
        this.client = client;
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
