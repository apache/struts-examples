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

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONWriter;

import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Customized JSONWriter using Flexjson
 */
public class FlexJSONWriter implements JSONWriter {
    private String dateFormatter;

    public String write(Object object) throws JSONException {
        return this.write(object, null, null, false);
    }

    public String write(Object object, Collection<Pattern> excludeProperties, Collection<Pattern> includeProperties,
                        boolean excludeNullProperties) throws JSONException {

        JSONSerializer serializer = new JSONSerializer();
        if (excludeProperties != null) {
            for (Pattern p : excludeProperties) {
                serializer = serializer.exclude(p.pattern());
            }
        }
        if (includeProperties != null) {
            for (Pattern p : includeProperties) {
                serializer = serializer.include(p.pattern());
            }
        }
        if (excludeNullProperties) {
            serializer = serializer.transform(new ExcludeTransformer(), void.class);
        }
        if (dateFormatter != null) {
            serializer = serializer.transform(new DateTransformer(dateFormatter), Date.class);
        }
        return serializer.serialize(object);
    }

    public void setIgnoreHierarchy(boolean ignoreHierarchy) {
    }

    public void setEnumAsBean(boolean enumAsBean) {
    }

    public void setDateFormatter(String dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public void setCacheBeanInfo(boolean cacheBeanInfo) {
    }

    public void setExcludeProxyProperties(boolean excludeProxyProperties) {
    }
}
