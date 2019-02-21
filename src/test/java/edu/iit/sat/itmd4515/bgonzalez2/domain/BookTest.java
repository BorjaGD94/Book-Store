/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.time.LocalDate;
import java.time.Month;
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
public class BookTest extends AbstractJPATest {

    private static final Logger LOG = Logger.getLogger(BookTest.class.getName());
    
    //testing my read operation.
    @Test
    public void testFindExistingBook() {
        // Knowing the PK we would just have to use em.find
        // This lets us use named parameters and queries
        Book seed = em.createNamedQuery("Book.findByTitle", Book.class)
                .setParameter("title","SEED")
                .getSingleResult();
        
        assertTrue("Title should match", "SEED".equals(seed.getTitle()));
    }
    
    // Test a new removal by creating a new pet and then removing it
    // Assert that you can no longer find it from the database
    @Test
    public void testRemoveBook() {
        Book b = new Book("Title to Remove", "Author to Remove", "Genre to Remove", LocalDate.of(2010, 1, 7));
        
        // Add Book to the database
        tx.begin();
        em.persist(b);
        tx.commit();
        
        assertNotNull("Book should be available in the database", b.getId());
        
        tx.begin();
        em.remove(b);
        tx.commit();
        
        Book removedBook = em.find(Book.class, b.getId());
        assertNull(removedBook);
    }
    
    @Test
    public void testUpdateBook() {
        Book book = new Book("Title to update", "Author 1", "Genre 1", LocalDate.of(2010, 1, 7));

        // Persist the object
        tx.begin();
        em.persist(book);

        assertNotNull(book.getId());
        assertEquals(book.getTitle(), "Title to update");

        book.setTitle("Updated Title");
        assertEquals(book.getTitle(), "Updated Title");

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
        
        assertNotNull("ID should be populated after commi");
        
        LOG.info(b.toString());
        assertTrue("ID should be greater than 0", b.getId() > 0l);
    }
}
