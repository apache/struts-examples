package org.apache.struts.tutorials.wildcardmethod.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.struts.tutorials.wildcardmethod.model.Person;



public class PersonService {
	
	//Create a Logger object
	private static final Logger logger = Logger.getLogger( PersonService.class.getName() );

	
	private static SortedMap<Integer, Person> people = new TreeMap<Integer, Person>();
	
	static {
		
		people.put(0, new Person(0, "Steve", "Smith") );
		people.put(1, new Person(1, "Sue", "Jones") );
		
		
	}
	
	
	private static List<Person> personList = new ArrayList<Person>();
	
	static{
		
		for (Integer id : people.keySet() ) {
			
			personList.add( people.get(id));
			
		}
	
	}
	
	
	public static List<Person> getPersons() {
		
		return personList;
		
	}
	
	
    public  void save(Person person) {
    	
    	int newId = getNewId();
    	
    	person.setId( newId );

    	people.put( newId, person);
    	
    	rebuildPersonList();
    	
    }
    
    
    private int getNewId() {
	
    	int newId = 0;
    	
    	try {
    		
    	  newId = people.lastKey() + 1;
    	 
    	} catch (NoSuchElementException ex) {
    		
    		logger.info("Id value set to 0");
    		
    	}
 
    	return newId;
    	
    	
	}


	public  void update(Person person) {
    	
    	
    	
    	Person tempPerson = people.get( person.getId() );
    	
    	tempPerson.setFirstName( person.getFirstName() );
    	tempPerson.setLastName(person.getLastName() );
    	
    	rebuildPersonList();
     	
    	
    }


	public void deletePerson(int ID) {
		
		
		people.remove(ID);
		
		rebuildPersonList();
		
	}


	public Person getPerson(int id) {

		return people.get(id);
		
	}
	
 
	public static void rebuildPersonList() {
		
		
    	personList = new ArrayList<Person>();
    	
        for (Integer id : people.keySet() ) {
 			
 			personList.add( people.get(id));
 			
 		}
        
        
		
		
	}
	

}
