package org.apache.struts.register.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.register.model.Person;

import java.util.ArrayList;
import java.util.List;

public class Register extends ActionSupport {

    private Person personBean;
    private List<Integer> options;

    public String execute2() throws Exception {
        //call Service class to store personBean's state in database
        return SUCCESS;
    }

    public String input() throws Exception {
        this.options = new ArrayList<>();
        this.options.add(3);
        return INPUT;
    }

    public String cancel2() throws Exception {
        return SUCCESS;
    }

    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        personBean = person;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }
}
