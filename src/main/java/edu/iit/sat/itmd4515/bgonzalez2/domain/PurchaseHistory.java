/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Borja
 */

@Entity
public class PurchaseHistory extends AbstractIdentifiedEntity {
      
    private LocalDate purchaseDate;
    
    @ManyToOne  
    private Client client;
    @OneToOne
    private Book book;

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
     * @param book new value of book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    
    public PurchaseHistory() {
        
    }

    public PurchaseHistory(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    
    

    /**
     * Get the value of purchaseDate
     *
     * @return the value of purchaseDate
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Set the value of purchaseDate
     *
     * @param purchaseDate new value of purchaseDate
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
