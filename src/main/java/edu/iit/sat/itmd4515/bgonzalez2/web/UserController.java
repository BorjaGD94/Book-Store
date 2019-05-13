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
import edu.iit.sat.itmd4515.bgonzalez2.service.UserService;
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
public class UserController {

    private static final Logger LOG = Logger.getLogger(ClientController.class.getName());

    @EJB
    private UserService userSvc;

    @Inject
    LoginController loginController;

    private Book book;
    private User user;


    @PostConstruct
    private void postContsruct(){
        LOG.info("UserController is firing postConstruct()");
            user = userSvc.findByUsername(loginController.getRemoteUser());
            book = new Book();
    }
        
    /**
     *
     */
    public UserController() {
        
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
   
}


