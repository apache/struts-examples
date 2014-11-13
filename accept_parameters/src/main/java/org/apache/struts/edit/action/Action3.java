package org.apache.struts.edit.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * The only accepted parameter is someParameter, so para1 and para2 should be empty
 */
public class Action3 extends ActionSupport implements Preparable {
	
	private static final Logger LOGGER = Logger.getLogger(Action3.class );
	private static final long serialVersionUID = 1L;
	
	private String para1, para2;

	@Override
	public void prepare() throws Exception {
		LOGGER.info("prepare: Both parameters shouldn't be present, since we only accept a different parameter");
		LOGGER.info("para1:" + para1);
		LOGGER.info("para2:" + para2);
	}

	public String execute() throws Exception {
		LOGGER.info("execute: After prepare both parameters should be present. Parameters Interceptor from basicStack is called.");
		LOGGER.info("para1:" + para1);
		LOGGER.info("para2:" + para2);
		return SUCCESS;
	}

	public String getPara1() {
		return para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public String getPara2() {
		return para2;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

}
