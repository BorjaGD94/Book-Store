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
        // Initialize the objects to be used in this controller
        book = new Book();
        user = new User();
        userDetails = new AbstractNamedEntity() {};
        admin = new Administrator();
    }
    
    /**
     *
     * @return all the books in the database
     */
    public List<Book> getAllBooks(){
        return bookSvc.findAll();
    }
    
    /**
     *
     * @param b contains the book owned by the client who wants to view their books
     * @return empty for null clients and the name of the client if the client exists
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
     * @param b contains the book that wants to be viewed
     * @return string with the path to view a book
     */
    public String prepareViewBook(Book b) {
        LOG.info("Inside prepareViewBook with book " + b.toString());
        // Set the book to be viewed as the controllers book
        this.book = b;
        return "/admin/viewBook.xhtml";
    }

    /**
     *
     * @param b contains the books that the admin wants to edit
     * @return string with the path to edit the book
     */
    public String prepareEditBook(Book b) {
        LOG.info("Inside prepareEditBook with book " + b.toString());
        // Set the book to be viewed as the controllers book
        this.book = b;
        return "/admin/editBook.xhtml";
    }

    /**
     *
     * @return string with the path to create a book
     */
    public String prepareCreateBook() {
        LOG.info("Inside prepareCreateBook");
        // The controllers book is created so when the user inputs the book info,
        // it can be set to this new book object.
        this.book = new Book();
        
        return "/admin/editBook.xhtml";

    }
    
    /**
     *
     * @return string with the path to create a user
     */
    public String prepareCreateUser() {
        LOG.info("Inside prepareCreateUser");
        // The controllers user is created so when the admin inputs the user info,
        // it can be set to this new user object.
        this.user = new User();
        return "/admin/addUser.xhtml";
    }
    
    /**
     *
     * @return string with the path to view the clients profile information
     */
    public String prepareViewClients() {
        LOG.info("Inside prepareViewClients");
        this.user = new User();
        return "/admin/viewClients.xhtml";
    }
    
    /**
     *
     * @return string with the path to view all the retailers
     */
    public String prepareViewRetailers() {
        LOG.info("Inside prepareViewClients");
        this.user = new User();
        return "/admin/viewRetailers.xhtml";
    }

    //
    // action methods

    /**
     *
     * @return string with the path to the admins welcome page
     */
    public String doSaveBook() {
        // when complete, this method will invoke service layer
        // this method will need to be smart enough to know when to edit, and when to create
        LOG.info("Inside doSaveBook with book " + book.toString());
        
        if(this.book.getId() != null){
            LOG.info("Preparing to call an update in the service layer with " + this.book.toString());
            // call the update function passing the book with the updated information.
            bookSvc.update(book);
        } else {
            LOG.info("Preparing to create in the service layer with " + this.book.toString());
            // call the function to create a book and set the client for that book.
            bookSvc.createAndAddClient(book);
        }
        
        
        return "/admin/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @param b contains the book to be deleted
     * @return string with the path the admins welcome page
     */
    public String doDeleteBook(Book b) {
        LOG.info("Inside doDeleteBook with book " + b.toString());
        bookSvc.remove(b);
        return "/admin/welcome.xhtml";
    }
    
    /**
     *
     *
     * @return string with the path to the admins welcome page
     */
    public String addUser() {
        LOG.info("Inside addUser with user " + user.toString());
        LOG.info("UserDet: " + userDetails.toString());
        
        userSvc.createUserWithGroups(user, userDetails);
        
        return "/admin/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     *
     *
     * @return string with the path to the admins welcome page
     */
    public String updateUser() {
        LOG.info("Inside update User with admin " + admin.toString());
        LOG.info("Inside update User with username: " + user.getUserName());
        adminSvc.updateAdminInfo(admin, user.getUserName());
        
        return "/admin/welcome.xhtml?faces-redirect=true";
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
