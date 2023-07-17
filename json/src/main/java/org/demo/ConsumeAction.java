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

package org.demo;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class ConsumeAction extends ActionSupport implements ServletRequestAware {

    private MyBean bean = new MyBean();
    private boolean responseAsJson = true;

    public String execute() throws Exception {
        if (responseAsJson) {
            return "JSON";
        }
        return SUCCESS;
    }

    public MyBean getBean() {
        return bean;
    }

    public void withServletRequest(HttpServletRequest request) {
        responseAsJson = request.getHeader("Accept").contains("application/json");
    }
}
