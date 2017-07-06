package example.struts2shiro.action;

public class LogoutAction extends ShiroBaseAction
{
    private static final long serialVersionUID = 1L;

    @Override
    public String execute()
    {
        if (isAuthenticated())
        {
            getShiroUser().logout(); // isAuthenticated = true -> getShiroUser() != null
        }
        
        return SUCCESS;
    }
    
}
