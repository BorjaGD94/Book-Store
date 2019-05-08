/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.AbstractNamedEntity;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Administrator;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import edu.iit.sat.itmd4515.bgonzalez2.service.AdminService;
import edu.iit.sat.itmd4515.bgonzalez2.service.BookService;
import edu.iit.sat.itmd4515.bgonzalez2.service.GroupService;
import edu.iit.sat.itmd4515.bgonzalez2.service.UserService;
import java.util.List;
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
public class AdminController {
    
    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());
    
    @EJB 
    private BookService bookSvc;
    
    @EJB 
    private AdminService adminSvc;
    
    @EJB 
    private UserService userSvc;
    
    @Inject 
    LoginController loginController;
    
    private Administrator admin;
    private Book book;  
    private User user;
    private AbstractNamedEntity userDetails;

    /**
     * Get the value of userDetails
     *
     * @return the value of userDetails
     */
    public AbstractNamedEntity getUserDetails() {
        return userDetails;
    }

    /**
     * Set the value of userDetails
     *
     * @param userDetails new value of userDetails
     */
    public void setUserDetails(AbstractNamedEntity userDetails) {
        this.userDetails = userDetails;
    }

    
    /**
     *
     */
    public AdminController() {
    }  
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside AdminController.postConstruct()");
        //admin = adminSvc.findByUsername(loginController.getRemoteUser());
        book = new Book();
        user = new User();
        userDetails = new AbstractNamedEntity() {};
    }
    
    /**
     *
     * @return
     */
    public List<Book> getAllBooks(){
        return bookSvc.findAll();
    }
    
    /**
     *
     * @param b
     * @return
     */
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

    /**
     *
     * @param b
     * @return
     */
    public String prepareViewBook(Book b) {
        LOG.info("Inside prepareViewBook with book " + b.toString());
        this.book = b;
        return "/admin/viewBook.xhtml";
    }

    /**
     *
     * @param b
     * @return
     */
    public String prepareEditBook(Book b) {
        LOG.info("Inside prepareEditBook with book " + b.toString());
        this.book = b;
        return "/admin/editBook.xhtml";
    }

    /**
     *
     * @return
     */
    public String prepareCreateBook() {
        LOG.info("Inside prepareCreateBook");
        this.book = new Book();
        
        return "/admin/editBook.xhtml";

    }
    
    public String prepareCreateUser() {
        LOG.info("Inside prepareCreateUser");
        this.user = new User();
        return "/admin/addUser.xhtml";
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

    /**
     *
     * @param b
     * @return
     */
    public String doDeleteBook(Book b) {
        LOG.info("Inside doDeleteBook with book " + b.toString());
        bookSvc.remove(b);
        return "/admin/welcome.xhtml";
    }
    
    public void addUser() {
        LOG.info("Inside addUser with user " + user.toString());
        LOG.info("UserDet: " + userDetails.toString());
        
        userSvc.createUserWithGroups(user, userDetails);
        
        //return "/admin/welcome.xhtml?faces-redirect=true";
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

    /**
     * Get the value of admin
     * 
     * @return the value of admin
     */
    public Administrator getAdmin() {
        admin = adminSvc.findByUsername(loginController.getRemoteUser());
        return admin;
    }

    /**
     * Set the value of admin
     * 
     * @param admin new value of admin
     */
    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }    
    
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
