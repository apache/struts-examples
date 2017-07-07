package org.apache.struts2.shiro.example.action;

import org.apache.shiro.subject.Subject;

import com.opensymphony.xwork2.ActionSupport;


public class ShiroBaseAction extends ActionSupport
{
    private static final long serialVersionUID = 1L;
    
    private transient Subject shiroUser;

    public boolean isAuthenticated() 
    {
        return shiroUser != null && shiroUser.isAuthenticated();
    }

    public Subject getShiroUser() 
    {
        return shiroUser;
    }

    public void setShiroUser(Subject shiroUser) 
    {
        this.shiroUser = shiroUser;
    }
}
