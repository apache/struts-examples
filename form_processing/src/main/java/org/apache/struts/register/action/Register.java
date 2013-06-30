package org.apache.struts.register.action;

import org.apache.struts.register.model.Person;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Acts as a controller to handle actions
 * related to registering a user.
 * @author bruce phillips
 *
 */
public class Register extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private Person personBean;


	public String execute() throws Exception {
		
		//call Service class to store personBean's state in database
		
		return SUCCESS;
		
	}
	
	public Person getPersonBean() {
		
		return personBean;
		
	}
	
	public void setPersonBean(Person person) {
		
		personBean = person;
		
	}

}
