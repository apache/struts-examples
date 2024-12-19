/**
 *
 */
package org.apache.struts2.shiro.example.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.apache.struts2.ActionInvocation;
import org.apache.struts2.interceptor.Interceptor;

/**
 * Inserts the current Shiro user into the value stack so that it can be
 * injected into Struts 2 actions should they have a JavaBeans setter
 * <code>setShiroUser(org.apache.shiro.subject.Subject shiroUser)</code>.
 */
public class ShiroUserInterceptor implements Interceptor {

    @Override
    public void destroy()
    {
        //release resources here
    }

    @Override
    public void init()
    {
        // create resources here
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
        if (actionInvocation.getAction() instanceof org.apache.struts2.shiro.example.action.ShiroBaseAction)
        {
            Subject shiroUser = SecurityUtils.getSubject();
            actionInvocation.getStack().setValue("shiroUser", shiroUser);
        }

        return actionInvocation.invoke();
    }

}
