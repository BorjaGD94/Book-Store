/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.Client;
import edu.iit.sat.itmd4515.bgonzalez2.service.ClientService;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
/**
 *
 * @author Borja
 */
@FacesConverter(value = "clientConverter", managed = true)
public class ClientConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(ClientConverter.class.getName());
    
    
    @Inject
    private ClientService clientSvc;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        LOG.info("Value is: " + value);
        
        if (value == null || value.isEmpty()) {
            return null;
        }

        return clientSvc.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        LOG.info("Object is: " + value.toString());
        
        if (value == null) {
            return "";
        }

        return String.valueOf(((Client) value).getId());
    }

}
