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
package org.apache.struts.examples.parameter;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import java.util.ArrayList;
import java.util.List;

public class IndexAction extends ActionSupport {

    private final List<User> users = new ArrayList<>();
    private Admin admin;
    
    public String execute() throws Exception {
        if (users.isEmpty()) {
            users.add(new User(1, "Luk"));
            users.add(new User(2, "Jan"));
        }
        if (admin == null) {
            admin = new Admin("Michal");
        }
        return SUCCESS;
    }

    @StrutsParameter(depth = 2)
    public List<User> getUsers() {
        return users;
    }

    @StrutsParameter(depth = 2)
    public Admin getAdminUser() {
        return admin;
    }

    @StrutsParameter(depth = 2)
    public void setAdminUser(Admin admin) {
        this.admin = admin;
    }
}