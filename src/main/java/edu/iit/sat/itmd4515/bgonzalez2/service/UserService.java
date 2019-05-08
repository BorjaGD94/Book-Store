/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.AbstractNamedEntity;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.Group;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Borja
 */

@Stateless
public class UserService extends AbstractService<User> {

    /**
     *
     */
    public UserService() {
        super(User.class);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", entityClass).getResultList();
    }
    
    public void createUserWithGroups(User userFromForm, AbstractNamedEntity aneFromForm){
        List<Group> groups = new ArrayList<>(userFromForm.getGroups());

        // first thing - create the pet
        userFromForm.setGroups(null);
        em.persist(userFromForm);
        em.flush();
        // now the pet has a database generated ID
        //Group adminGroup = em.getReference(Group.class, )
        // just to be sure and prove - let's get a reference using the generated ID
        groups.forEach((Group g) -> {
            // for each owner passed in from the admin form, add the pet.
            if (g.getGroupName().equals("Administrator")){
                //userFromForm.addGroup();
            }
            //g.addPet(petInDatabase);
            //em.merge(o);
        });
    }
    
}
