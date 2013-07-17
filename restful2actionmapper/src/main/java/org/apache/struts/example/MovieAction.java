package org.apache.struts.example;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class MovieAction extends ActionSupport {
	
	private static final Logger LOGGER = Logger.getLogger(MovieAction.class.getName() );

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/**
	 * Method is called if url is move/[any value but new] 
	 * and http get request.  The value after the /
	 * will be provided to the setId method.
	 * @return success
	 */
	public String view() {
		
		LOGGER.debug("Value of id is " + id);
		
		return SUCCESS;
		
	}
	
	/**
	 * Method is called if url is movie/ .
	 * No value will be sent to setId.
	 * @return
	 */
	public String index() {
		
		
		LOGGER.debug("Value of id is " + id);
		
		return SUCCESS;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
