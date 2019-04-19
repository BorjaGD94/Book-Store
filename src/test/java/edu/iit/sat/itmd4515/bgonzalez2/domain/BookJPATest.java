/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.time.LocalDate;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Borja
 */
public class BookJPATest extends AbstractJPATest {

    private static final Logger LOG = Logger.getLogger(BookJPATest.class.getName());
    
    //testing my read operation.
    @Test
    public void testFindExistingBook() {
        // Knowing the PK we would just have to use em.find
        // This lets us use named parameters and queries
        Book seed = em.createNamedQuery("Book.findByTitle", Book.class)
                .setParameter("title","SEED")
                .getSingleResult();
        
        assertTrue("Title should match", "SEED".equals(seed.getTitle()));
        LOG.info(seed.toString());
    }
    
    // Test a new removal by creating a new book and then removing it
    // Assert that you can no longer find it in the database
    @Test
    public void testRemoveBook() {
        Book b = new Book("Title to Remove", "Author to Remove", "Genre to Remove", LocalDate.of(2010, 1, 7));
        
        // Add Book to the database
        tx.begin();
        em.persist(b);
        tx.commit();
        
        // After the commit the id should be present in the database
        assertNotNull("Book should be available in the database", b.getId());
        LOG.info(b.toString());
        
        tx.begin();
        em.remove(b);
        tx.commit();
        
        // After remove and commit there should be no trace of the book in the database
        Book removedBook = em.find(Book.class, b.getId());
        assertNull(removedBook);
    }
    
    @Test
    public void testUpdateBook() {
        Book book = new Book("Title to update", "Author 1", "Genre 1", LocalDate.of(2010, 1, 7));

        // Persist the object
        tx.begin();
        em.persist(book);
        
        // Assert the the new book is what it is suppossed to be
        assertEquals(book.getTitle(), "Title to update");
        LOG.info(book.toString());
        
        // Update the contents of the new book
        book.setTitle("Updated Title");
        
        // Assert the the updated book is what it is suppossed to be
        assertEquals(book.getTitle(), "Updated Title");
        LOG.info(book.toString());

        tx.commit();
        
        //clean up test data
        tx.begin();
        em.remove(book);
        tx.commit();
    }
    
    @Test
    public void testCreateNewValidBook() {
        Book b = new Book("New Title", "New Author", "New Genre", LocalDate.of(2010, 1, 7));
        
        tx.begin();
        
        assertNull("ID should be null before em.persist and commit",b.getId());
        em.persist(b);
        
        assertNull("How about now? ID should be null after em.persist and before commit", b.getId());
        tx.commit();
        
        assertNotNull("ID should be populated after commit", b.getId());
        
        LOG.info(b.toString());
        assertTrue("ID should be greater than 0", b.getId() > 0l);
        
        //clean up test data
        tx.begin();
        em.remove(b);
        tx.commit();
    }
    
    //expected failure scenario. My expected exception is too general in this case... would be better if it was something specific
    @Test(expected = RollbackException.class)
    public void testCreateInvalidBookDatabaseFail() {
        Book b = new Book(null, "New Author", "New Genre", LocalDate.of(2010, 1, 7));
        
        tx.begin();
        
        assertNull("ID should be null before em.persist and commit",b.getId());
        em.persist(b);
        
        assertNull("How about now? ID should be null after em.persist and before commit", b.getId());
        tx.commit();
        
        assertNotNull("ID should be populated after commit");
        
        LOG.info(b.toString());
        assertTrue("ID should be greater than 0", b.getId() > 0l);
    }
    
    @Test
    public void testBookClientOneToManyBiDirectionalRelationship() {
        Book b = new Book("Relationship Title", "New Relationship Author", "Genre", LocalDate.of(2010, 1, 7));

        Client c = new Client("Francisco", "Gonzalez", "francisco@iit.com");

        // set the inverse side of the relationship and observe
        //p.getOwners().add(o);
        // result - by setting only the inverse side of the relationship, JoinTable was not updated
        // set the owning side of the relationship and observe
        b.setClient(c);
        c.addBook(b);
        //c.getBooks().add(b);

        tx.begin();
        em.persist(c);
        em.persist(b);
        tx.commit();

        // find the owning side and illustrate
        Client findClient = em.find(Client.class, 1l);
        System.out.println("Owner name is: " + findClient.getName());
        assertEquals(c.getName(), findClient.getName());
        System.out.println("Owner books: " + findClient.getBooks().toString());
        assertEquals(c.getBooks().get(0).getTitle(), findClient.getBooks().get(0).getTitle());

        // find the inverse and illustrate
        Book findBook = em.createNamedQuery("Book.findByTitle", Book.class)
                .setParameter("title","Relationship Title")
                .getSingleResult();
        System.out.println("Book tittle is: " + findBook.getTitle());
        assertEquals(b.getTitle(), findBook.getTitle());
        System.out.println("Book owner: " + findBook.getClient().toString());
        assertEquals(b.getClient().getName(), findBook.getClient().getName());
        
        // clean up test data
        tx.begin();
        c.removeBook(b);
        em.remove(b);
        tx.commit();
        
        findClient = em.find(Client.class, 1l);
        assertTrue("Client books should be 0", findClient.getBooks().size() == 0);
    }
}
