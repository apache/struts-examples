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

package org.apache.struts.example;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.model.ThemeDescriptor;
import org.apache.struts.model.Themes;

import java.util.Map;

public class ThemeAction extends ActionSupport {

    private static final Logger LOG = LogManager.getLogger(ThemeAction.class);

    private Map<String, ThemeDescriptor> themes = Themes.list();
    private ThemeDescriptor selectedTheme = Themes.get("simple");

    public String execute() throws Exception {
        return SUCCESS;
    }

    public Map<String, ThemeDescriptor> getThemes() {
        return themes;
    }

    public ThemeDescriptor getSelectedTheme() {
        LOG.info("Existing theme: #0", String.valueOf(selectedTheme));
        return selectedTheme;
    }

    public void setSelectedTheme(ThemeDescriptor selectedTheme) {
        LOG.info("Selected theme: #0", String.valueOf(selectedTheme));
        this.selectedTheme = selectedTheme;
    }
}
