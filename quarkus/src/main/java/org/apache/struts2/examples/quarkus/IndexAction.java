package org.apache.struts2.examples.quarkus;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.Action;
import org.apache.struts2.result.Result;

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
