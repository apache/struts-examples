package org.apache.struts.edit.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * In struts.xml no accepted parameter are defined, so we should accept everything
 */
public class Action2 extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String para1, para2;

	public String execute() throws Exception {
		System.out.println("para1:" + para1);
		System.out.println("para2:" + para2);
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
