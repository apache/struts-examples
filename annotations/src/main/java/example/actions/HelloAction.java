package example.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Acts as a controller to handle actions
 * related to registering a user.
 *
 * @author bruce phillips
 */
public class HelloAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(HelloAction.class);

    private String message;


    public String execute() throws Exception {

        log.info("In execute method of class Hello");

        message = "Hello from Struts 2 with no XML configuration.";

        return SUCCESS;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


}
