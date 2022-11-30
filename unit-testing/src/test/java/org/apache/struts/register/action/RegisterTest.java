/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts.register.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.junit.StrutsTestCase;
import org.junit.Test;

public class RegisterTest extends StrutsTestCase {

    @Test
    public void testExecuteValidationPasses() throws Exception {
        request.setParameter("personBean.firstName", "Bruce");
        request.setParameter("personBean.lastName", "Phillips");
        request.setParameter("personBean.email", "bphillips@ku.edu");
        request.setParameter("personBean.age", "19");

        ActionProxy actionProxy = getActionProxy("/register.action");

        Register action = (Register) actionProxy.getAction();
        assertNotNull("The action is null but should not be.", action);

        String result = actionProxy.execute();
        assertEquals("The execute method did not return " + ActionSupport.SUCCESS + " but should have.", ActionSupport.SUCCESS, result);
    }

    @Test
    public void testExecuteValidationFailsMissingFirstName() throws Exception {
        request.setParameter("personBean.firstName", "Bruce");
        request.setParameter("personBean.lastName", "Phillips");
        request.setParameter("personBean.email", "bphillips@ku.edu");
        request.setParameter("personBean.age", "17");

        ActionProxy actionProxy = getActionProxy("/register.action");

        Register action = (Register) actionProxy.getAction();
        assertNotNull("The action is null but should not be.", action);

        String result = actionProxy.execute();
        assertEquals("The execute method did not return " + ActionSupport.INPUT + " but should have.", ActionSupport.INPUT, result);
    }

    @Test
    public void testExecuteValidationFailsAgeToYoung() throws Exception {
        request.setParameter("personBean.lastName", "Phillips");
        request.setParameter("personBean.email", "bphillips@ku.edu");
        request.setParameter("personBean.age", "19");

        ActionProxy actionProxy = getActionProxy("/register.action");

        Register action = (Register) actionProxy.getAction();
        assertNotNull("The action is null but should not be.", action);

        String result = actionProxy.execute();
        assertEquals("The execute method did not return " + ActionSupport.INPUT + " but should have.", ActionSupport.INPUT, result);
    }

}
