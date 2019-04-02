/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import javax.persistence.Entity;

/**
 *
 * @author Borja
 */

@Entity
public class Administrator extends AbstractNamedEntity {

    public Administrator() {
    }

    public Administrator(String name, String userName, String password) {
        super(name, userName, password);
    }
    
}
