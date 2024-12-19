package org.apache.struts.helloworld.action;

import org.apache.struts2.ActionSupport;

import java.util.concurrent.TimeUnit;

public class ExecAndWaitTestAction extends ActionSupport {

    public String test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(15);
        return SUCCESS;
    }

}
