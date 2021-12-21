package org.apache.struts_example;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.UUID;

public class IndexAction extends ActionSupport {

    private String entityId;
    private String processId;
    private String deleteItem;

    @Override
    public String execute() throws Exception {
        entityId = UUID.randomUUID().toString();
        processId = Thread.currentThread().getName();
        deleteItem = String.format("Entity %s", entityId);
        return Action.SUCCESS;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getProcessId() {
        return processId;
    }

    public String getDeleteItem() {
        return deleteItem;
    }
}
