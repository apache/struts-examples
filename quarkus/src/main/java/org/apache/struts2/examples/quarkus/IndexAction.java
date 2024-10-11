package org.apache.struts2.examples.quarkus;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class IndexAction {

    private String message;
    private String name;

    public Result execute() {
        return invocation -> {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.getWriter().println("Hello!");
        };
    }

    public String hello() {
        message = "Hello " + name;
        return Action.SUCCESS;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }
}
