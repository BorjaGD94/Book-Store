/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Retailer;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.Group;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.time.LocalDate;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;


/**
 *
 * @author Borja
 */

@Startup
@Singleton
public class StartupSeedDatabase {

    private static final Logger LOG = Logger.getLogger(StartupSeedDatabase.class.getName());
    
    @EJB
    private BookService bookSvc;
    
    @EJB
    private ClientService clientSvc;
    
    @EJB
    private RetailerService retailerSvc;
    
    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;

    public StartupSeedDatabase() {
    }
    
    @PostConstruct
    private void seedDatabase(){
        
        // create admin user and group
        User adminUser = new User("admin", "admin", true);
        Group adminGroup = new Group("ADMIN_GROUP", "This is the identity store group for administrators.  It is managed by security and operations folks who care deeply about security.");
        adminUser.addGroup(adminGroup);
        groupSvc.create(adminGroup);
        userSvc.create(adminUser);
        
        // create business users and groups
        Group clientGroup = new Group("CLIENT_GROUP", "Identity Store group of clients");
        Group retailerGroup = new Group("RETAILER_GROUP", "Identity Store group of retailers");
        User ret1 = new User("ret1", "ret1", true);
        ret1.addGroup(retailerGroup);
        User ret2 = new User("ret2", "ret2", true);
        ret2.addGroup(retailerGroup);
        ret2.addGroup(clientGroup);
        User client1 = new User("client1", "client1", true);
        client1.addGroup(clientGroup);
        User client2 = new User("client2", "client2", true);
        client2.addGroup(clientGroup);
        groupSvc.create(retailerGroup);
        groupSvc.create(clientGroup);
        userSvc.create(ret1);
        userSvc.create(ret2);
        userSvc.create(client1);
        userSvc.create(client2);
        
        
        Client c1 = new Client("name1");
        c1.setUser(client1);
        Client c2 = new Client("name2");
        c2.setUser(client2);
        Retailer r1 = new Retailer("retName1");
        r1.setUser(ret1);
        Retailer r2 = new Retailer("retName2");
        r2.setUser(ret2);
        
        
        Book b1 = new Book("EJBBook", "EJBAuthor", "EJBGenre", LocalDate.of(2010, 3, 12));
        b1.setClient(c1);
        
        Book b2 = new Book("EJBBook1", "EJBAuthor1", "EJBGenre1", LocalDate.of(2007,11, 29));
        b2.setClient(c2);

        bookSvc.create(b1);
        bookSvc.create(b2);
        clientSvc.create(c1);
        clientSvc.create(c2);
        retailerSvc.create(r1);
        retailerSvc.create(r2);
        
        for(Book b : bookSvc.findAll()){
            LOG.info(b.toString());
            LOG.info("\t" + b.getClient().toString());
        }
    }
}
