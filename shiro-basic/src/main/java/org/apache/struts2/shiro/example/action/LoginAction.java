package org.apache.struts2.shiro.example.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginAction extends ActionSupport implements Preparable
{

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = LogManager.getLogger(LoginAction.class);

    private String username;
    private String password;
    private transient Subject shiroUser;
    
    @Override
    public void prepare() throws Exception 
    {
        shiroUser = SecurityUtils.getSubject();
    }
    
    @Override
    public String execute()
    {
        String result = INPUT;
        
        if (shiroUser != null)
        {
            // Do some stuff with a Session
            Session session = shiroUser.getSession();
            session.setAttribute("MyUsername", username);
            log.info("Saving 'username' value to session [" + username + "]");
            
            // let's login the current user so we can check against roles and permissions:
            if (! shiroUser.isAuthenticated()) 
            {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                token.setRememberMe(true);
                try 
                {
                    shiroUser.login(token);                
                    result = SUCCESS;
                } 
                catch (UnknownAccountException uae) 
                {
                    addActionError("There is no user with username of '" + token.getPrincipal() + "'");
                    log.error(uae.getMessage());
                } 
                catch (IncorrectCredentialsException ice) 
                {
                    addActionError("Password for account '" + token.getPrincipal() + "' was incorrect!");
                    log.error(ice.getMessage());
                } 
                catch (LockedAccountException lae) 
                {
                    addActionError("The account for username '" + token.getPrincipal() + "' is locked.  " +
                            "Please contact your administrator to unlock it.");
                    log.error(lae.getMessage());
                }
                // ... catch more exceptions here (maybe custom ones specific to your application?
                catch (AuthenticationException ae) 
                {
                    addActionError("An authentication exception has occurred trying to login user: " + token.getPrincipal());
                    log.error(ae.getMessage());
                }        
            }
            else if (shiroUser.isAuthenticated())
            {
                result = SUCCESS;
            }
        }
                
        return result;
    }
    
    public Subject getShiroUser() 
    {
        return shiroUser;
    }

    public void setShiroUser(Subject shiroUser) 
    {
        this.shiroUser = shiroUser;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }    
    
}
