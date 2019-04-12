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
public class Retailer extends AbstractNamedEntity {
    
    @OneToMany(mappedBy = "retailer")
    private List<Book> books = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public Retailer() {
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

    
    public Retailer(String name) {
        super(name);
    } 
    
}
