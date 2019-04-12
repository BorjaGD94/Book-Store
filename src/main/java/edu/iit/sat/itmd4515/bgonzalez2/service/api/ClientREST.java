/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.service.api;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.service.ClientService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Borja
 */

@Path("/v1/clients")
public class ClientREST {
    
    @EJB ClientService clientService;
    
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String pingMe(){
        return "Thou has been pinged from /v1/owners";
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Client> getClients(@QueryParam("name") String name){
        
        List<Client> results = new ArrayList<>();
        
        if (null == name){
            results = clientService.findAll();
        } else {
            results.add(clientService.findByName(name));
        }
        return results;
    }
    
    @GET
    @Path("/{id}")
    public Client getClientById(@PathParam("id") Long id){
        return clientService.find(id);
    }
    
//    @GET
//    public Client getClientByName(@PathParam("name") String name){
//        return clientService.findByName(name);
//    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Client createNewClient(Client c){
        return clientService.createClient(c);
    }
}
