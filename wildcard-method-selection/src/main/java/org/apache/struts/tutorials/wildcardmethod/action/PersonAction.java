package org.apache.struts.tutorials.wildcardmethod.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.tutorials.wildcardmethod.model.Person;
import org.apache.struts.tutorials.wildcardmethod.service.PersonService;

import java.util.List;


public class PersonAction extends ActionSupport {

    //Create a Logger object
    private static final Logger log = LogManager.getLogger(PersonAction.class);
    private static final long serialVersionUID = 1L;

    Person person;
    int id;
    List<Person> personList = PersonService.getPersons();
    PersonService personService = new PersonService();

    public String execute() throws Exception {
        log.debug("In execute method");
        return SUCCESS;
    }

    public String create() {
        log.debug("In create method");
        person = new Person();

        return INPUT;
    }

    public String runCreateThis() {
        log.debug("In create method");
        person = new Person();

        return INPUT;
    }

    public String edit() {
        log.debug("In edit method");
        person = personService.getPerson(id);

        return INPUT;
    }

    public String saveOrUpdate() {
        log.debug("In saveOrUpdate method");
        if (person.getId() > -1) {
            personService.update(person);
        } else {
            personService.save(person);
        }

        personList = PersonService.getPersons();
        return SUCCESS;
    }


    public String delete() {
        log.debug("In delete method");
        personService.deletePerson(id);

        personList = PersonService.getPersons();

        return SUCCESS;
    }


    public Person getPerson() {
        return person;
    }


    public void setPerson(Person person) {
        this.person = person;
    }


    public List<Person> getPersonList() {
        return personList;
    }


    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
}
