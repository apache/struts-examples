package example.actions;

import example.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

/**
 * Acts as a controller to handle actions
 * related to registering a user.
 *
 * @author bruce phillips
 */
public class RegisterAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(RegisterAction.class);

    private Person personBean;

    @Action("register-input")
    public String input() throws Exception {

        log.info("In input method of class RegisterAction");

        return INPUT;
    }

    public String execute() throws Exception {

        log.info("In execute method of class RegisterAction");

        //call Service class to store personBean's state in database

        return SUCCESS;
    }

    @StrutsParameter(depth = 10)
    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        personBean = person;
    }

}
