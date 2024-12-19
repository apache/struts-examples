package org.apache.struts.register.action;

import org.apache.struts.register.model.Person;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

/**
 * Acts as a controller to handle actions
 * related to registering a user.
 *
 * @author bruce phillips
 */
public class Register extends ActionSupport {

    private Person personBean;

    public String execute() throws Exception {
        //call Service class to store personBean's state in database
        return SUCCESS;
    }

    public void validate() {
        if (personBean.getFirstName() == null || personBean.getFirstName().isEmpty()) {
            addFieldError("personBean.firstName", "First name is required.");
        }

        if (personBean.getEmail() == null || personBean.getEmail().isEmpty()) {
            addFieldError("personBean.email", "Email is required.");
        }

        if (personBean.getAge() < 18) {
            addFieldError("personBean.age", "Age is required and must be 18 or older");
        }

    }

    @StrutsParameter(depth = 1)
    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        personBean = person;
    }

}
