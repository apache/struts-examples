package org.apache.struts.examples.sitemesh3;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class HelloAction extends ActionSupport {

    private static final Map<String, String> DECORATORS = Collections.unmodifiableSortedMap(new TreeMap<>() {{
        put("1", "Decorator 1");
        put("2", "Decorator 2");
        put("3", "Exclude from decorating");
    }});

    private String decorator;

    @Override
    public String execute() throws Exception {
        if ("1".equals(decorator)) {
            return SUCCESS;
        } else if ("2".equals(decorator)) {
            return "other";
        }
        addActionError("Wrong decorator: " + decorator);
        return ERROR;
    }

    public String getDecorator() {
        return decorator;
    }

    @StrutsParameter
    public void setDecorator(String decorator) {
        this.decorator = decorator;
    }

    public Map<String, String> getDecorators() {
        return DECORATORS;
    }
}
