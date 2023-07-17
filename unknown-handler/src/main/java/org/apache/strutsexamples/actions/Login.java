package org.apache.strutsexamples.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.action.SessionAware;

import java.util.Map;

public class Login extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    private String email;
    private String password;

    public String execute() {
        return SUCCESS;
    }

    @Action(value = "login-submit")
    public String submit() {
        if ("lukasz@demo.org".equals(email) && "secret".equals(password)) {
            session.put("logged", Boolean.TRUE);
            addActionMessage("Logged in!");
            return "home";
        } else {
            addActionError("Cannot login!");
            return "login";
        }
    }

    public void withSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
