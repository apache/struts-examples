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

package org.apache.struts_example;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.opensymphony.xwork2.config.Configuration;

public class IndexAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * This a bit of hack as factory defined in "struts.xml" will be used by default,
     * so we must exactly tell which implementation we want to use here
     */
    @Override
    protected TextProvider getTextProvider() {
        return container.getInstance(TextProviderFactory.class, "struts").createInstance(getClass());
    }
}
