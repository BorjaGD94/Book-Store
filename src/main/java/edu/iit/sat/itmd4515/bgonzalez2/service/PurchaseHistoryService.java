/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service;

import edu.iit.sat.itmd4515.bgonzalez2.domain.PurchaseHistory;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Borja
 */

@Stateless
public class PurchaseHistoryService extends AbstractService<PurchaseHistory>{

    public PurchaseHistoryService() {
        super(PurchaseHistory.class);
    }

    @Override
    public List<PurchaseHistory> findAll() {
        return em.createNamedQuery("PurchaseHistory.findAll", entityClass).getResultList();
    }
    
}
