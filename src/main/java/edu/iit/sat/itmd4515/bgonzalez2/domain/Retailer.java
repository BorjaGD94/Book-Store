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
public class Retailer extends AbstractNamedEntity {
    
    @OneToMany(mappedBy = "retailer")
    private List<Book> books = new ArrayList<>();

    public Retailer() {
    }
    
    public Retailer(String name, String userName, String password) {
        super(name, userName, password);
    }
    
    
    
}
