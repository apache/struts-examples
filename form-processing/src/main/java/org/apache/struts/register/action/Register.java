package org.apache.struts.register.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.register.model.Person;

public class Register extends ActionSupport {

    private Person personBean;

    public String execute2() throws Exception {
        //call Service class to store personBean's state in database
        return SUCCESS;
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

}
