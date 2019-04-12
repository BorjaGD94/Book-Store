/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Borja
 */

@Entity
public class Administrator extends AbstractNamedEntity {
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public Administrator() {
    }

    public Administrator(String name) {
        super(name);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
     
}
