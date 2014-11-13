package org.apache.struts.edit.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * In struts.xml no accepted parameter are defined, so we should accept everything
 */
public class Action2 extends ActionSupport implements Preparable {
	
	private static final Logger LOGGER = Logger.getLogger(Action2.class );
	private static final long serialVersionUID = 1L;
	
	private String para1, para2;

	@Override
	public void prepare() throws Exception {
		LOGGER.info("prepare: Both parameters shouldn't be present, we have defined no special behavior in struts.xml and prepare interecptor is called prior to params interceptor.");
		LOGGER.info("para1:" + para1);
		LOGGER.info("para2:" + para2);
	}

	public String execute() throws Exception {
		LOGGER.info("execute: After params interceptor is called, both parameters should be present, we have defined no special behavior in struts.xml");
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
