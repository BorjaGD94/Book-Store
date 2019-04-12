/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Borja
 */

@Stateless
public class UserService extends AbstractService<User> {

    public UserService() {
        super(User.class);
    }
    
    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", entityClass).getResultList();
    }
    
}
