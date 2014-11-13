package org.apache.struts.edit.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * In struts.xml the only accepted parameter is para1, para2 should be null
 */
public class Action1 extends ActionSupport implements Preparable {
	
	private static final Logger LOGGER = Logger.getLogger(Action1.class );
	private static final long serialVersionUID = 1L;
	
	private String para1, para2;
	
	@Override
	public void prepare() throws Exception {
		LOGGER.info("prepare: Only para1 should be present. We have defined in struts.xml that it is the only parameter we accept.");
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
