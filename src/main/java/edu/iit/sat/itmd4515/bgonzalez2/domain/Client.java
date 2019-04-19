/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Borja
 */

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "Client.findAll", query = "select c from Client c")
@NamedQuery(name = "Client.findByName", query = "select c from Client c where c.name = :name")
@NamedQuery(name = "Client.findByUsername", query = "select c from Client c where c.user.userName = :username")
public class Client extends AbstractNamedEntity {
    
    @OneToMany(mappedBy = "client")
    @XmlTransient
    @JsonbTransient
    private List<PurchaseHistory> purchases = new ArrayList<>();
    
    @OneToMany(mappedBy = "client")
    @XmlTransient
    @JsonbTransient 
    private List<Book> books = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;
    
    
    public Client() {
    }
    
    public Client(String name, String lastName, String email) {
        super(name, lastName, email);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
}
