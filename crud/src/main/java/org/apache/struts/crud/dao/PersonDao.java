package org.apache.struts.crud.dao;

import org.apache.struts.crud.model.Person;

/**
 * @author antonio sanchez
 */
public interface PersonDao {
    
    Person getPerson(Integer id);
    
    Person[] getAllPersons();

    void updatePerson(Person personBean);
    
    void insertPerson(Person personBean);
    
    void deletePerson(Integer id);
}
