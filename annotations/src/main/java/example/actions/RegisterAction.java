package example.actions;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

import example.model.Person;

/**
 * Acts as a controller to handle actions
 * related to registering a user.
 * @author bruce phillips
 *
 */
public class RegisterAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger( RegisterAction.class.getName() );
	
	private Person personBean;
	
	@Action("register-input")
	public String input() throws Exception {

		logger.info("In input method of class RegisterAction");
		
		return INPUT;

	}

	public String execute() throws Exception {
		
		logger.info("In execute method of class RegisterAction");
		
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
