package org.apache.examples.struts.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.rest.ContentTypeHandlerManager;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerInterceptor extends MethodFilterInterceptor {

    private static final Logger log = LogManager.getLogger(ExceptionHandlerInterceptor.class);

    private static final String ACTION_ERROR = "actionError";

    private ContentTypeHandlerManager manager;

    @Inject
    public void setContentTypeHandlerManager(ContentTypeHandlerManager mgr) {
        this.manager = mgr;
    }

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        try{
            return actionInvocation.invoke();
        } catch (Exception exception){
            log.warn("Exception occurred: {}", exception.getMessage());
            Map<String, Object> errors = new HashMap<>();

            HttpHeaders httpHeaders = new DefaultHttpHeaders()
                    .disableCaching()
                    .withStatus(HttpServletResponse.SC_BAD_REQUEST)
                    .renderResult(Action.INPUT);

                if(exception instanceof SecurityException) {
                    errors.put(ACTION_ERROR, "Operation not allowed!");
                    httpHeaders.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }  else {
                    errors.put(ACTION_ERROR, exception.getMessage());
                }
            return manager.handleResult(actionInvocation, httpHeaders, errors);
        }
    }
}
