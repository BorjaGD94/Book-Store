/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.security.Group;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Borja
 */

@Named
@Stateless
public class GroupService extends AbstractService<Group> {

    private static final Logger LOG = Logger.getLogger(GroupService.class.getName());
    
    
    /**
     *
     */
    public GroupService() {
        super(Group.class);
    }
    
    /**
     *
     * @return
     */
    public List<Group> findAll() {
        List<Group> grs = em.createNamedQuery("Group.findAll", entityClass).getResultList();
        grs.forEach((Group g) -> {
            // for each owner passed in from the admin form, add the pet.
            LOG.info("Group Name: " + g.getGroupName());
        });
        return em.createNamedQuery("Group.findAll", entityClass).getResultList();
    }
    
    /**
     *
     * @param groupName
     * @return
     */
    public Group findByGroupName(String groupName) {
        LOG.info("The group name is: " + groupName);
        return em.createNamedQuery("Group.findByGroupName", Group.class).setParameter("groupName", groupName).getSingleResult();
    }
    
}
