/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.bgonzalez2.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Borja
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    // login form fields
    @NotBlank(message = "You must enter a username")
    private String username;
    @NotBlank(message = "You must enter a password")
    private String password;

    @Inject
    private SecurityContext securityContext;
    @Inject
    private FacesContext facesContext;
    

    /**
     *
     */
    public LoginController() {
    }

    // authenticated username from JSR-375 security

    /**
     *
     * @return
     */
    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    /**
     *
     * @return
     */
    public String getRoles() {
        return securityContext.toString();
    }
    
    /**
     *
     * @return
     */
    public boolean isRetailer(){
        return securityContext.isCallerInRole("RETAILER_ROLE");
    }
    
    /**
     *
     * @return
     */
    public boolean isClient(){
        return securityContext.isCallerInRole("CLIENT_ROLE");
    }
    
    /**
     *
     * @return
     */
    public boolean isAdmin(){
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    // action methods

    /**
     *
     * @return
     */
    public String doLogin() {
        LOG.info("inside doLogin");
        Credential credential = new UsernamePasswordCredential(username, new Password(password));

        AuthenticationStatus status = securityContext.authenticate(
                (HttpServletRequest) facesContext.getExternalContext().getRequest(),
                (HttpServletResponse) facesContext.getExternalContext().getResponse(),
                AuthenticationParameters.withParams().credential(credential)
        );

        LOG.info("AuthenticationStatus is: " + status.toString());

        switch (status) {
            case SEND_CONTINUE:
                LOG.info("SEND_CONTINUE in login");
                break;
            case SEND_FAILURE:
                LOG.info("SEND_FAILURE in login");
                return "/error.xhtml";
            case SUCCESS:
                LOG.info("SUCCESS in login");
                break;
            case NOT_DONE:
                LOG.info("NOT_DONE in login");
                return "/error.xhtml";
        }

        return "/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String doLogout() {

        try {
            HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            req.logout();

        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return "/error.xhtml";
        }

        LOG.info("Logged out and redirecting to login page");
        return "/login.xhtml?faces-redirect=true";

    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of password
     * 
     * @return the value of password
     */
    public String getPassword() {
        LOG.info("Password: " + password);
        return password;
    }

    /**
     * Set the value of password
     * 
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
