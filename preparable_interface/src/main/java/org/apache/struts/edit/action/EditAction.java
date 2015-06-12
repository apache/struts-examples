package org.apache.struts.edit.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.edit.model.Person;
import org.apache.struts.edit.model.State;
import org.apache.struts.edit.service.CarModelsService;
import org.apache.struts.edit.service.CarModelsServiceHardCoded;
import org.apache.struts.edit.service.EditService;
import org.apache.struts.edit.service.EditServiceInMemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Acts as a controller to handle actions
 * related to editing a Person.
 *
 * @author bruce phillips
 */
public class EditAction extends ActionSupport implements Preparable {

    private static Logger log = LogManager.getLogger(EditAction.class);
    private static final long serialVersionUID = 1L;

    private EditService editService = new EditServiceInMemory();
    private CarModelsService carModelsService = new CarModelsServiceHardCoded();

    private Person personBean;
    private String[] sports = {"football", "baseball", "basketball"};
    private String[] genders = {"male", "female", "not sure"};
    private List<State> states;
    private String[] carModelsAvailable;


    @Override
    public void prepare() throws Exception {
        log.debug("In prepare method...");
        carModelsAvailable = carModelsService.getCarModels();
        setPersonBean(editService.getPerson());
    }

    public void prepareExecute() {
        log.debug("In prepareExecute method...");
    }

    public String execute() throws Exception {
        log.debug("In execute method...");
        editService.savePerson(getPersonBean());
        return SUCCESS;
    }

    public void prepareInput() {
        log.debug("In prepareInput method...");
    }

    public String input() throws Exception {
        log.debug("In input method...");
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

        states = new ArrayList<>();
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
