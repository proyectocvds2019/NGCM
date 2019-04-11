package edu.eci.cvds.bean;

import java.io.File;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import org.apache.shiro.crypto.hash.Sha256Hash;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginBean")
@ViewScoped
public class loginBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(loginBean.class);

    private String username;
    private String password;
    private Boolean rememberMe;

    public loginBean() {
        
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    
    /**
     * Try and authenticate the user
     */
    public void doLogin() {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), new Sha256Hash(getPassword()).toHex(), getRememberMe());

        try {
            subject.login(token);

            if (subject.hasRole("admin")) {
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
        this.password = senha;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean lembrar) {
        this.rememberMe = lembrar;
    }
}
