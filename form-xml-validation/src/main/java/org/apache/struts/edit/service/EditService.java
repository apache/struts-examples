package org.apache.struts.edit.service;

import org.apache.struts.edit.model.Person;

public interface EditService {
	
	
	Person getPerson() ;

	void savePerson(Person personBean);

}
