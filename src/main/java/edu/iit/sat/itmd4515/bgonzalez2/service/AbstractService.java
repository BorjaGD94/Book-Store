/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Borja
 * @param <T>
 */
public abstract class AbstractService<T> {
    
    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    
    /**
     *
     */
    protected Class<T> entityClass;
    
    /**
     *
     * @param entityClass
     */
    protected AbstractService(Class entityClass){
        this.entityClass = entityClass;
    }
    
    /**
     *
     * @param entity
     */
    public void create(T entity){
        em.persist(entity);
    }
    
    /**
     *
     * @param entity
     */
    public void update(T entity){
        em.merge(entity);
    }
    
    /**
     *
     * @param entity
     */
    public void remove(T entity){
        em.remove(em.merge(entity));
    }
    
    /**
     * Find a Client
     * 
     * @param id The PK of entity
     * @return returns an object or null
     */
    public T find(Object id){
        return em.find(entityClass, id);
    }
    
    /**
     *
     * @return
     */
    public abstract List<T> findAll();
        //return em.createNamedQuery("Client.findAll", Client.class).getResultList();
}
