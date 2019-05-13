/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.AbstractNamedEntity;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Administrator;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.domain.Retailer;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.Group;
import edu.iit.sat.itmd4515.bgonzalez2.domain.security.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Borja
 */

@Stateless
public class UserService extends AbstractService<User> {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

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
   
    
    /**
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        LOG.info("The username is: " + username);
        return em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getSingleResult();
    }
    
    /**
     *
     * @param userFromForm
     * @param aneFromForm
     */
    public void createUserWithGroups(User userFromForm, AbstractNamedEntity aneFromForm){
        List<Group> groups = new ArrayList<>(userFromForm.getGroups());
        User u = new User(userFromForm.getUserName(), userFromForm.getPassword(), true);
        // first thing - create the user
        u.setGroups(groups);
        em.persist(u);
        em.flush();
        // now the user has a database generated ID
        // just to be sure and prove - let's get a reference using the generated ID
        groups.forEach((Group g) -> {
            // for each owner passed in from the admin form, add the pet.
            userFromForm.addGroup(g);
            em.merge(userFromForm);
            if (g.getGroupName().equals("ADMIN_GROUP")){
                Administrator a = new Administrator(aneFromForm.getName(), aneFromForm.getLastName(), aneFromForm.getEmail());
                a.setUser(userFromForm);
                em.persist(a);
            }
            else if (g.getGroupName().equals("CLIENT_GROUP")){
                Client c = new Client(aneFromForm.getName(), aneFromForm.getLastName(), aneFromForm.getEmail());
                c.setUser(userFromForm);
                em.persist(c);
            }
            if (g.getGroupName().equals("RETAILER_GROUP")){
                Retailer r = new Retailer(aneFromForm.getName(), aneFromForm.getLastName(), aneFromForm.getEmail());
                r.setUser(userFromForm);
                em.persist(r);
            }
        });
    }
    
}
