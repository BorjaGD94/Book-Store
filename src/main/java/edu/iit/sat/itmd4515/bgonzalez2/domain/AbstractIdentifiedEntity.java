/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.domain;

import edu.iit.sat.itmd4515.bgonzalez2.service.api.LocalDateTimeXmlAdapter;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Borja
 */

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractIdentifiedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Version
    private Long version;
    
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime lastUpdatedTimestamp;
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime createdTimestamp;
    
    @PrePersist
    private void prePersist() {
        createdTimestamp = LocalDateTime.now();
        lastUpdatedTimestamp = LocalDateTime.now();      
    }
    
    @PreUpdate
    private void preUpdate() {
        lastUpdatedTimestamp = LocalDateTime.now();      
    }
    
    /**
     * Get the value of createdTimestamp
     *
     * @return the value of createdTimestamp
     */
    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Set the value of createdTimestamp
     *
     * @param createdTimestamp new value of createdTimestamp
     */
    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    /**
     * Get the value of lastUpdatedTimestamp
     *
     * @return the value of lastUpdatedTimestamp
     */
    public LocalDateTime getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    /**
     * Set the value of lastUpdatedTimestamp
     *
     * @param lastUpdatedTimestamp new value of lastUpdatedTimestamp
     */
    public void setLastUpdatedTimestamp(LocalDateTime lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * Get the value of version
     *
     * @return the value of version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Set the value of version
     *
     * @param version new value of version
     */
    public void setVersion(Long version) {
        this.version = version;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
