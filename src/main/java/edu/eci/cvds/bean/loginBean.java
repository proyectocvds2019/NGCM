package edu.eci.cvds.bean;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.faces.application.FacesMessage;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginBean")
@ViewScoped
public class loginBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(loginBean.class);
    private String username;
    private String password;
    private Subject subject;


    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    
    /**
     * Try and authenticate the user
     */
    public void doLogin() {
       subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword());

        try {
            subject.login(token);

            if (subject.hasRole("admin")) {
            	subject.getSession().setAttribute("correo", username);
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
            }
            else if(subject.hasRole("employee")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("employees/index.xhtml");
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("open/index.xhtml");
            }
        }
        catch (UnknownAccountException ex) {
            facesError("Unknown account");
            log.error(ex.getMessage(), ex);
        }
        catch (IncorrectCredentialsException ex) {
            facesError("Wrong password "+this.username+" "+this.password+" "+getUsername()+" "+getPassword());
            log.error(ex.getMessage(), ex);
        }
        catch (LockedAccountException ex) {
            facesError("Locked account");
            log.error(ex.getMessage(), ex);
        }
        catch (AuthenticationException ex) {
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
        catch (IOException ex){
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
            
        }
        finally {
            token.clear();
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public void verificarInicioSesion()throws IOException{
        if(SecurityUtils.getSubject().getSession().getAttribute("correo") != null){
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/admin/index.xhtml");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = new Sha256Hash(senha).toHex();
    }

    
    
    
    

}
