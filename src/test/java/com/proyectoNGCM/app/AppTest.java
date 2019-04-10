package com.proyectoNGCM.app;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void deberíaIniciarSesion() {
    	//1.
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.
        SecurityManager securityManager = factory.getInstance();

        //3.
        SecurityUtils.setSecurityManager(securityManager);
        Subject usuarioActual = SecurityUtils.getSubject();
        Session session = usuarioActual.getSession();
        UsernamePasswordToken token = new UsernamePasswordToken("administrador","123");
        token.setRememberMe(true);
        usuarioActual.login(token);
        assertTrue(usuarioActual.isAuthenticated());
    }
    
    public void noDeberiaInicarSesion() {
    	//1.
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.
        SecurityManager securityManager = factory.getInstance();

        //3.
        SecurityUtils.setSecurityManager(securityManager);
        Subject usuarioActual = SecurityUtils.getSubject();
        Session session = usuarioActual.getSession();
        UsernamePasswordToken token = new UsernamePasswordToken("administrador","1234");
        token.setRememberMe(true);
        usuarioActual.login(token);
        assertFalse(usuarioActual.isAuthenticated());
    }
}
