package org.apache.struts2.shiro.example.action;

import org.apache.shiro.session.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WelcomeAction extends ShiroBaseAction
{
    private static final long serialVersionUID = 1L;
    private static final Logger log = LogManager.getLogger(WelcomeAction.class);

    private String username;
    
    @Override
    public String execute()
    {
        String result = ERROR;
        
        if (isAuthenticated())
        {
            // Retrieve value from session
            Session session = getShiroUser().getSession();
            String value = (String) session.getAttribute("MyUsername");
            if (value.equals(username)) 
            {
                log.info("Retrieved the correct 'username' value [" + value + "] from session");
            }
        
            printRoles();
            printPermissions();
            result = SUCCESS;
        }
        
        return result;
    }
    
    public void printRoles()
    {
        if (getShiroUser().hasRole("admin"))
        {
            log.info("User '" + username + "' has role of 'admin'");
        }
        else
        {
            log.info("User '" + username + "' is missing role 'admin'");
        }
        
        if (getShiroUser().hasRole("schwartz"))
        {
            log.info("User '" + username + "' has role of 'schwartz'");
        }
        else
        {
            log.info("User '" + username + "' is missing role 'schwartz'");
        }
        
        if (getShiroUser().hasRole("goodguy"))
        {
            log.info("User '" + username + "' has role of 'goodguy'");
        }
        else
        {
            log.info("User '" + username + "' is missing role 'goodguy'");
        }
    }

    public void printPermissions()
    {
        if (getShiroUser().isPermitted("lightsaber"))
        {
            log.info("User '" + username + "' has 'lightsaber' permission");
        }
        else
        {
            log.info("User '" + username + "' is missing permission 'lightsaber'");
        }
        
        if (getShiroUser().isPermitted("winnebago"))
        {
            log.info("User '" + username + "' has 'winnebago' permission");
        }
        else
        {
            log.info("User '" + username + "' is missing permission 'winnebago'");
        }
        
        if (getShiroUser().isPermitted("winnebago:drive:eagle5"))
        {
            log.info("User '" + username + "' has 'winnebago:drive:eagle5' permission");
        }
        else
        {
            log.info("User '" + username + "' is missing permission 'winnebago:drive:eagle5'");
        }
    }
    
    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

}
