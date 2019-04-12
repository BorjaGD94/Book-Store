/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain.security;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Borja
 */
public class UserListener {
    
    @Inject private Pbkdf2PasswordHash passwordHash;
    
    @PrePersist
    @PreUpdate
    private void hashThePassword(User u){
        u.setPassword(passwordHash.generate(u.getPassword().toCharArray()));
    }
}
