/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Borja
 */

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "Administrator.findAll", query = "select a from Administrator a")
@NamedQuery(name = "Administrator.findByName", query = "select a from Administrator a where a.name = :name")
@NamedQuery(name = "Administrator.findByUsername", query = "select a from Administrator a where a.user.userName = :username")
public class Administrator extends AbstractNamedEntity {
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Administrator() {
    }

    /**
     *
     * @param name
     * @param lastName
     * @param email
     */
    public Administrator(String name, String lastName, String email) {
        super(name, lastName, email);
    }
    
    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
     
}
