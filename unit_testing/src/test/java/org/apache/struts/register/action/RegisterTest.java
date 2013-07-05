package org.apache.struts.register.action;



import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterTest extends StrutsTestCase {

	@Test
	public void testExecuteValidationPasses() throws Exception {
		
		request.setParameter("personBean.firstName", "Bruce");
		
		request.setParameter("personBean.lastName", "Phillips");
		
		request.setParameter("personBean.email", "bphillips@ku.edu");
		
		request.setParameter("personBean.age", "19");
			
		ActionProxy actionProxy = getActionProxy("/register.action") ;
		
		Register action = (Register) actionProxy.getAction();
		
		assertNotNull("The action is null but should not be.", action);

		String result = actionProxy.execute();
		
		assertEquals("The execute method did not return " + ActionSupport.SUCCESS + " but should have.", ActionSupport.SUCCESS, result);

	}
	
	@Test
	public void testExecuteValidationFailsMissingFirstName() throws Exception {
		
		request.setParameter("personBean.firstName", "Bruce");
		
		request.setParameter("personBean.lastName", "Phillips");
		
		request.setParameter("personBean.email", "bphillips@ku.edu");
		
		request.setParameter("personBean.age", "17");
			
		ActionProxy actionProxy = getActionProxy("/register.action") ;
		
		Register action = (Register) actionProxy.getAction();
		
		assertNotNull("The action is null but should not be.", action);

		String result = actionProxy.execute();
		
		assertEquals("The execute method did not return " + ActionSupport.INPUT + " but should have.", ActionSupport.INPUT, result);

	}
	
	
	@Test
	public void testExecuteValidationFailsAgeToYoung() throws Exception {
		
		
		request.setParameter("personBean.lastName", "Phillips");
		
		request.setParameter("personBean.email", "bphillips@ku.edu");
		
		request.setParameter("personBean.age", "19");
			
		ActionProxy actionProxy = getActionProxy("/register.action") ;
		
		Register action = (Register) actionProxy.getAction();
		
		assertNotNull("The action is null but should not be.", action);

		String result = actionProxy.execute();
		
		assertEquals("The execute method did not return " + ActionSupport.INPUT + " but should have.", ActionSupport.INPUT, result);

	}

}
