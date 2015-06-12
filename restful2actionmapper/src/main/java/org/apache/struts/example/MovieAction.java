package org.apache.struts.example;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovieAction extends ActionSupport {

	private static final Logger log = LogManager.getLogger(MovieAction.class.getName());
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/**
	 * Method is called if url is move/[any value but new] 
	 * and http get request.  The value after the /
	 * will be provided to the setId method.
	 * @return success
	 */
	public String view() {
		log.debug("Value of id is " + id);
		return SUCCESS;
	}
	
	/**
	 * Method is called if url is movie/ .
	 * No value will be sent to setId.
	 * @return
	 */
	public String index() {
		log.debug("Value of id is " + id);
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
