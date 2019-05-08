/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service.api;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.service.ClientService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Borja
 */

@WebService
public class ClientSOAP {
    
    private static final Logger LOG = Logger.getLogger(ClientSOAP.class.getName());

    @EJB ClientService clientService;
    
    /**
     *
     */
    public ClientSOAP() {
    }
    
    /**
     *
     * @param name
     * @param lastName
     * @param email
     * @return
     */
    @WebMethod
    public Client createNewClient(String name, String lastName, String email){
        LOG.info("Inside createNewOwner SOAP Service with " + name);
        return clientService.createClient(new Client(name, lastName, email));
    }
}
