package org.apache.strutsexamples.actions;

import org.apache.struts2.ActionSupport;

public class Index extends ActionSupport {

    public String execute() {
        return "home";
    }

}
