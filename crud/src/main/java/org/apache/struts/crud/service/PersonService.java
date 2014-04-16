package org.apache.struts.crud.service;

import org.apache.struts.crud.model.Country;
import org.apache.struts.crud.model.Person;

public interface PersonService {

    Person getPerson(Integer id);
    
    Person[] getAllPersons();

    void updatePerson(Person personBean);
    
    void insertPerson(Person personBean);
    
    void deletePerson(Integer id);
    
    Country[] getCountries();
    
    String[] getCarModels();
    
    String[] getSports();
    
    String[] getGenders();
}