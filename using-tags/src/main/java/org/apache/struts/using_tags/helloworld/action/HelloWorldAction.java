package org.apache.struts.using_tags.helloworld.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.using_tags.helloworld.model.MessageStore;

/**
 * Acts as a Struts 2 controller that responds
 * to a user action by setting the value
 * of the MessageStore model class, and returns a String 
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
        
        helloCount++;
        
        return SUCCESS;
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }

}
