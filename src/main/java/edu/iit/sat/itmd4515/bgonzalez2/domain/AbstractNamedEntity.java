/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Borja
 */

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractIdentifiedEntity {
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false, unique = true)
    protected String name;
    @NotBlank(message = "Last name is required")
    @Column(nullable = false, unique = true)
    protected String lastName;
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    protected String email;
    
    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
        
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final AbstractNamedEntity other = (AbstractNamedEntity) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

       
}
