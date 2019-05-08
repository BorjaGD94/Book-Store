/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Borja
 */
@Entity
@NamedQuery(name = "Retailer.findAll", query = "select r from Retailer r")
@NamedQuery(name = "Retailer.findByName", query = "select r from Retailer r where r.name = :name")
@NamedQuery(name = "Retailer.findByUsername", query = "select r from Retailer r where r.user.userName = :username")
public class Retailer extends AbstractNamedEntity {

    @OneToMany(mappedBy = "retailer")
    private List<Book> books = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Retailer() {
    }
    
    /**
     *
     * @param name
     * @param lastName
     * @param email
     */
    public Retailer(String name, String lastName, String email) {
        super(name, lastName, email);
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

    /**
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

}
