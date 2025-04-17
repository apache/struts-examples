package org.apache.struts.register.action;

import org.apache.struts.register.model.Person;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import java.util.List;

public class Register extends ActionSupport {

    private Person personBean;
    private List<Integer> options;

    public String execute2() throws Exception {
        //call Service class to store personBean's state in database
        return SUCCESS;
    }

    public String input() throws Exception {
        return INPUT;
    }

    @StrutsParameter(depth = 1)
    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        personBean = person;
    }

    @StrutsParameter(depth = 1)
    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }
}
