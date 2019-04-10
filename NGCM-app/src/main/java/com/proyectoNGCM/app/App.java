package com.proyectoNGCM.app;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final transient Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main( String[] args )
    {
        log.info("comenzando");
        //1.
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.
        SecurityManager securityManager = factory.getInstance();

        //3.
        SecurityUtils.setSecurityManager(securityManager);
        
        Subject usuarioActual = SecurityUtils.getSubject();
        Session session = usuarioActual.getSession();
        if(!usuarioActual.isAuthenticated()) {
        	System.out.println("no está autenticado");
        	UsernamePasswordToken token = new UsernamePasswordToken("administrador","123");
        	token.setRememberMe(true);
        	usuarioActual.login(token);
        	System.out.println("ya está autenticdo");
        }
        System.out.println(usuarioActual.isAuthenticated());
        System.exit(0);
        
    }
}
