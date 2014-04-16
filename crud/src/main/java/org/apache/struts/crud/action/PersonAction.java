package org.apache.struts.crud.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

//import org.apache.log4j.Logger;
import org.apache.struts.crud.model.Person;
import org.apache.struts.crud.model.Country;
import org.apache.struts.crud.service.PersonService;
import org.apache.struts.crud.service.DefaultPersonService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Acts as a controller to handle actions related to editing a Person.
 * 
 * TODO add comments to all code in the project
 * TODO display country name instead of code
 * TODO logging
 * TODO prepareInput 
 *
 * @author bruce phillips
 * @author antonio sánchez
 */
public class PersonAction extends ActionSupport implements Preparable {
//    private static Logger log = Logger.getLogger(PersonAction.class.getName());
    private PersonService personService = new DefaultPersonService();
    private Person person;
    private Person[] persons;
    private String[] sports;
    private String[] genders;
    private Country[] countries;
    private String[] carModelsAvailable;

    @Override
    public void prepare() throws Exception {
        carModelsAvailable = personService.getCarModels();
        sports = personService.getSports();
        countries = personService.getCountries();
        genders = personService.getGenders();
        
        if (person != null && person.getPersonId() != null) {
            person = personService.getPerson(person.getPersonId());
        }
    }

    public String list() {
        persons = personService.getAllPersons();
        return SUCCESS;
    }
    
    public String save() {
        if (person.getPersonId() == null) {
            personService.insertPerson(person);
        } else {
            personService.updatePerson(person);
        }
        return SUCCESS;
    }

    public String delete() {
        personService.deletePerson(person.getPersonId());
        return SUCCESS;
    }
    
    public Person[] getPersons() {
        return persons;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String[] getSports() {
        return sports;
    }

    public String[] getGenders() {
        return genders;
    }

    public Country[] getCountries() {
        return countries;
    }

    public String[] getCarModelsAvailable() {
        return carModelsAvailable;
    }
}

