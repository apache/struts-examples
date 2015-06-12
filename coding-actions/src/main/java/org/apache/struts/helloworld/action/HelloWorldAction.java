package org.apache.struts.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.helloworld.model.MessageStore;

/**
 * Acts as a Struts 2 controller that responds
 * to a user action by setting the value
 * of the Message model class, and returns a String 
 * result.
 * @author Bruce Phillips
 *
 */
public class HelloWorldAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The model class that stores the message
	 * to display in the view.
	 */
	private MessageStore messageStore;
	
	private static int helloCount = 0;
	
	public int getHelloCount() {
		return helloCount;
	}

	public void setHelloCount(int helloCount) {
		HelloWorldAction.helloCount = helloCount;
	}
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * Creates the MessageStore model object, 
	 * increase helloCount by 1 and 
	 * returns success.  The MessageStore model
	 * object will be available to the view.
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		
		messageStore = new MessageStore() ;
		
		//Action included a query string parameter of userName
		//or a form field with name of userName
		if (userName != null) {
			
			messageStore.setMessage( messageStore.getMessage() + " " + userName);
			
		}
		
		helloCount++;
		
		return SUCCESS;
	}

	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}
	
	

}
