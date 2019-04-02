/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Borja
 */

@Entity
public class Client extends AbstractNamedEntity {
    
    @OneToMany(mappedBy = "client")
    private List<PurchaseHistory> purchases = new ArrayList<>();
    
    @OneToMany(mappedBy = "client")
    private List<Book> books = new ArrayList<>();
    
    
    public Client() {
    }
    
    public Client(String name, String userName, String password) {
        super(name, userName, password);
    }
    
    
    /**
     * addBook is a helper method to manage both sides of this bi-directional
     * OneToMany relationship
     *
     * @param b
     */
    public void addBook(Book b) {
        if (!this.books.contains(b)) {
            this.books.add(b);
        }
        /*if (!b.getClient().equals(this)) {
            // For one to many relationships is this neccessary?
            b.setClient(this);
        }*/
    }

    /**
     * removeBook is a helper method to manage both sides of this bi-directional
     * OneToMany relationship
     *
     * @param b
     */

    public void removeBook(Book b) {
        if (this.books.contains(b)) {
            this.books.remove(b);
        }
        //if( b.getClient().equals(this)){
            // For one to many relationships is this neccessary?
            // b.getClient().remove(this);
        //}
    }
    
    /**
     * Get the value of books
     *
     * @return the value of books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Set the value of books
     *
     * @param books new value of books
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }


    /**
     * Get the value of purchases
     *
     * @return the value of purchases
     */
    public List<PurchaseHistory> getPurchases() {
        return purchases;
    }

    /**
     * Set the value of purchases
     *
     * @param purchases new value of purchases
     */
    public void setPurchases(List<PurchaseHistory> purchases) {
        this.purchases = purchases;
    }
    
    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + "}";
    }
        
}
