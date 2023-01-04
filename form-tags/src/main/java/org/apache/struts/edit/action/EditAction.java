package org.apache.struts.edit.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.edit.model.Person;
import org.apache.struts.edit.model.State;
import org.apache.struts.edit.service.EditService;
import org.apache.struts.edit.service.EditServiceInMemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Acts as a controller to handle actions
 * related to editing a Person.
 */
public class EditAction extends ActionSupport {

    private final EditService editService = new EditServiceInMemory();

    private Person personBean;

    private final String[] sports = {"football", "baseball", "basketball"};

    private final String[] genders = {"male", "female", "not sure"};

    private final String[] carModelsAvailable = {"Ford", "Chrysler", "Toyota", "Nissan"};

    public String execute() throws Exception {
        editService.savePerson(getPersonBean());
        return SUCCESS;
    }

    public String input() throws Exception {
        setPersonBean(editService.getPerson());
        return INPUT;
    }

    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        personBean = person;
    }

    public List<String> getSports() {
        return Arrays.asList(sports);
    }

    public List<String> getGenders() {
        return Arrays.asList(genders);
    }

    public List<State> getStates() {
        List<State> states = new ArrayList<>();
        states.add(new State("AZ", "Arizona"));
        states.add(new State("CA", "California"));
        states.add(new State("FL", "Florida"));
        states.add(new State("KS", "Kansas"));
        states.add(new State("NY", "New York"));

        return states;
    }

    public String[] getCarModelsAvailable() {
        return carModelsAvailable;
    }

}
