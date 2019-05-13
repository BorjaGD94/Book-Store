 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Administrator;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Book;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.domain.PurchaseHistory;
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
    private AdminService adminSvc;
    
    @EJB
    private PurchaseHistoryService purSvc;
    
    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;

    /**
     *
     */
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
        
        Administrator a = new Administrator("Borja","Gonzalez","bgonzalez@iit.com");
        a.setUser(adminUser);
        Client c1 = new Client("name1","lastName1","name1@iit.com");
        c1.setUser(client1);
        Client c2 = new Client("name2","lastName2","name2@iit.com");
        c2.setUser(client2);
        Retailer r1 = new Retailer("retName1","lastRetName1","ret1@iit.com");
        r1.setUser(ret1);
        Retailer r2 = new Retailer("retName2","lastRetName2","ret2@iit.com");
        r2.setUser(ret2);
        Client c3 = new Client("retName2","lastRetName2","ret2@iit.com");
        c3.setUser(ret2);
        
        Book b1 = new Book("EJBBook", "EJBAuthor", "EJBGenre", LocalDate.of(2010, 3, 12));
        c1.addBook(b1);
        b1.setClient(c1);
        r1.addBook(b1);
        b1.setRetailer(r1);
        
        Book b2 = new Book("EJBBook1", "EJBAuthor1", "EJBGenre1", LocalDate.of(2007,11, 29));
        c2.addBook(b2);
        b2.setClient(c2);
        r2.addBook(b2);
        b2.setRetailer(r2);
        
        // These are books that are not owned (Books available for purchase)
        Book b3 = new Book("AnyBook", "AnyAuthor", "AnyGenre", LocalDate.of(2010,06, 11));
        Book b4 = new Book("AnyBook1", "AnyAuthor1", "AnyGenre1", LocalDate.of(2010,10, 21));
        Book b5 = new Book("AnyBook2", "AnyAuthor2", "AnyGenre2", LocalDate.of(2005,03, 06));
        r2.addBook(b3);
        r2.addBook(b4);
        r1.addBook(b5);
        b3.setRetailer(r2);
        b4.setRetailer(r2);
        b5.setRetailer(r1);
        
        // The books that have clients set, should also have a purchase associated, 
        // because if a book belongs to a client he has purchased this book previously
        PurchaseHistory pur = new PurchaseHistory(LocalDate.now());
        PurchaseHistory pur1 = new PurchaseHistory(LocalDate.now());
        c2.addPurchase(pur);
        pur.setClient(c2);
        pur.setBook(b2);
        c1.addPurchase(pur1);
        pur1.setClient(c1);
        pur1.setBook(b1);

        bookSvc.create(b1);
        bookSvc.create(b2);
        bookSvc.create(b3);
        bookSvc.create(b4);
        bookSvc.create(b5);
        clientSvc.create(c1);
        clientSvc.create(c2);
        clientSvc.create(c3);
        retailerSvc.create(r1);
        retailerSvc.create(r2);
        purSvc.create(pur);
        purSvc.create(pur1);
        adminSvc.create(a);
        
        for(Book b : bookSvc.findAll()){
            LOG.info(b.toString());
            if(b.getClient() == null){
                continue;
            }else {
                LOG.info("\t" + b.getClient().toString());
            }
        }
    }
}
