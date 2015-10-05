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
package org.apache.examples.struts.actions;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;

public class IndexController extends RestActionSupport {

    private static final long serialVersionUID = 6153177836211979662L;

    private boolean useMinifiedResources = false;

    public HttpHeaders index() {
        return new DefaultHttpHeaders("index")
                .disableCaching();
    }

    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }

    public String edit() {
        return Action.SUCCESS;
    }

    public String editNew() {
        return Action.SUCCESS;
    }

    public boolean isUseMinifiedResources() {
        return useMinifiedResources;
    }

    public void setUseMinifiedResources(boolean useMinifiedResources) {
        this.useMinifiedResources = useMinifiedResources;
    }
}
