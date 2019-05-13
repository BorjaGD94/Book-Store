/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Borja
 */

@Entity
@Table(name = "sec_group")
@NamedQuery(name="Group.findAll", query = "select g from Group g")
@NamedQuery(name="Group.findByGroupName", query = "select g from Group g where g.groupName = :groupName")
public class Group {
    
    @Id
    private String groupName;
    private String groupDesc;
    
    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    /**
     *
     */
    public Group() {
    }
    
    /**
     *
     * @param groupName
     * @param groupDesc
     */
    public Group(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

    /**
     * Get the value of users
     *
     * @return the value of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set the value of users
     *
     * @param users new value of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    

    /**
     * Get the value of groupDesc
     *
     * @return the value of groupDesc
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * Set the value of groupDesc
     *
     * @param groupDesc new value of groupDesc
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }


    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" + "groupName=" + groupName + ", groupDesc=" + groupDesc + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.groupName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (!Objects.equals(this.groupName, other.groupName)) {
            return false;
        }
        return true;
    }
    
    
}
