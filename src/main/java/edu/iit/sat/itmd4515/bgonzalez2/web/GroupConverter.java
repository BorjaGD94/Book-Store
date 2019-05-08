/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import edu.iit.sat.itmd4515.bgonzalez2.domain.security.Group;
import edu.iit.sat.itmd4515.bgonzalez2.service.GroupService;
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
@FacesConverter(value = "groupConverter", managed = true)
public class GroupConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(GroupConverter.class.getName());
    
    

    @Inject
    private GroupService groupSvc;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        LOG.info("Value is: " + value);
        
        if (value == null || value.isEmpty()) {
            LOG.info("Value is null");
            return null;
        }

        return groupSvc.findByGroupName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        LOG.info("Object is: " + value.toString());
        
        if (value == null) {
            LOG.info("Object is null");
            return "";
        }

        return ((Group) value).getGroupName();
    }

}
