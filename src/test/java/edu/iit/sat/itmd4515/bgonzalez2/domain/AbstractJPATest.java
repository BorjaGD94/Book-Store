/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.time.LocalDate;
import java.time.Month;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Borja
 */
public class AbstractJPATest {
    private static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;
    
    @BeforeClass
    public static void beforeClassTestFixtureRunsOncePerClass() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }
    
    @AfterClass
    public static void afterClassTestFixtureRunsOncePerClass() {
        if (emf != null){
            emf.close();
        }
    }
     
    @Before
    public void beforeEachTestFixture() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        
        // Create my test data before each @Test case
        Book seed = new Book("SEED", "Book author", "Book Genre", LocalDate.of(2000, 12, 12));
        tx.begin();
        em.persist(seed);
        tx.commit();     
    }
    
    @After
    public void afterEachTestFixture() {
        
        // Clean up my test data after each @Test case
        Book seed = em.createNamedQuery("Book.findByTitle", Book.class)
                .setParameter("title","SEED")
                .getSingleResult();
        
        tx.begin();
        em.remove(seed);
        tx.commit();
        
        if (em != null){
            em.close();
        }
    }
}


