package org.apache.strutsexamples.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.UnknownHandler;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsException;
import org.apache.struts2.views.tiles.TilesResult;
import org.apache.tiles.api.TilesContainer;
import org.apache.tiles.api.access.TilesAccess;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class TilesUnknownHandler implements UnknownHandler {

    private static final Logger LOG = LogManager.getLogger(TilesUnknownHandler.class);

    public ActionConfig handleUnknownAction(String namespace, String actionName) throws StrutsException {
        return null;
    }

    public Result handleUnknownResult(ActionContext actionContext, String actionName, ActionConfig actionConfig, String resultCode) throws StrutsException {

        ServletContext servletContext = ServletActionContext.getServletContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        ServletApplicationContext context = new ServletApplicationContext(servletContext);
        TilesContainer container = TilesAccess.getContainer(context);

        String namespace = ServletActionContext.getActionMapping().getNamespace();
        Set<String> definitions = buildDefinitionNames(namespace, actionName, resultCode);

        for (String definition : definitions) {
            LOG.debug("Looking for tiles definition: {}", definition);

            if (container.isValidDefinition(definition, new ServletRequest(context, request, response))) {
                return new TilesResult(definition);
            }
        }

        LOG.warn("Couldn't find tiles definition for namespace {}, action name {} and result code {}", namespace, actionName, resultCode);
        return null;
    }

    protected Set<String> buildDefinitionNames(String namespace, String actionName, String resultCode) {
        Set<String> definitions = new LinkedHashSet<>();

        if (namespace.startsWith("/")) {
            namespace = namespace.substring(1);
        }

        if (!Objects.equals(namespace, "") && !Objects.equals(namespace, "/")){
            if (namespace.endsWith("/")) {
                definitions.add(namespace + actionName);
                definitions.add(namespace + actionName + "-" + resultCode);
                definitions.add(namespace + resultCode);
            } else {
                definitions.add(namespace + "/" + actionName);
                definitions.add(namespace + "/" + actionName + "-" + resultCode);
                definitions.add(namespace + "/" + resultCode);
            }
        }

        definitions.add(actionName);
        definitions.add(actionName + "-" + resultCode);

        LOG.debug("Possible definition's names {}", definitions);

        return definitions;
    }

    public Object handleUnknownActionMethod(Object action, String methodName) {
        return null;
    }
}
