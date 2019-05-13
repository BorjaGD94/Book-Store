 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.AbstractNamedEntity;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
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
    private User user;
    private AbstractNamedEntity userDetails;
 
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
     * @param b contains the book the user seleceted for viewing
     * @return a string with the path were the book can be viewed
     */
    public String prepareViewBook(Book b) {
        LOG.info("Inside prepareViewPet with pet " + b.toString());
        this.book = b;
        return "/client/viewBook.xhtml";
    }


    
    /**
     *
     * @param c is the client currently logged in 
     * @return the path were the client can view the available books
     */
    public String prepareViewBooks(Client c) {
        LOG.info("Inside prepareCreateBook");  
        this.client = c;
        return "/client/buyBook.xhtml";
    }
    
    /**
     *
     * @param c is the client currently logged in 
     * @return the path were the client can edit his profile information
     */  
    public String prepareEditClient(Client c) {
        LOG.info("Inside prepareEditClient with : " + c.toString());
        
        //Get the info introduced by the client
        this.user = c.getUser();       
        this.userDetails = new AbstractNamedEntity(c.getName(), c.getLastName(), c.getEmail()) {};
        return "/client/editUser.xhtml";
    }
    
    
    /**
     *
     * @param a is the client currently logged in
     * @param u is the user currently logged in
     * 
     * @return the path to the welcome page
     */
    public String updateUser(Client a, User u){
        LOG.info("Inside update User with client " + a.toString());
        LOG.info("Inside update User with username: " + u.toString());
        clientSvc.updateClientInfo(a, u);
        
        return "/admin/welcome.xhtml?faces-redirect=true";
    }


    
    // action methods

    
    /**
     *
     * @param b contains the book that the client wants to buy
     * @param c contains the client currently loggen in and that wants to buy the book
     * 
     * @return a string with the path to the clients welcome page
     */
    public String doBuyBook(Book b, Client c) {
        LOG.info("Inside doBuyBook with book " + b.toString());
        clientSvc.associateBook(b, c);
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
     * @return the value of book
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
