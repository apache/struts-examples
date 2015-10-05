package org.apache.examples.struts.actions;

import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsRestTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class IndexControllerTest extends StrutsRestTestCase<IndexController> {


    @Before
    public void init() throws Exception {
        super.setUp();
    }

    @Test
    public void testIndex() throws Exception {
        ActionProxy proxy = getActionProxy("GET", "/index");
        String result = proxy.execute();
        assertThat(result, is(equalTo("index")));

        IndexController indexController = (IndexController) proxy.getAction();
        assertThat(indexController, notNullValue());
        assertThat(indexController.isUseMinifiedResources(), is(false));
    }

}