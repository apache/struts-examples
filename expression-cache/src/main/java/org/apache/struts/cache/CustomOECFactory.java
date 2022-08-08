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
package org.apache.struts.cache;

import com.opensymphony.xwork2.ognl.DefaultOgnlExpressionCacheFactory;
import com.opensymphony.xwork2.ognl.OgnlCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Placeholder custom OGNL expression cache factory
 *
 * Breakpoints:
 * 1) OgnlUtil (Struts Core) - 1st line of constructor (to see what factory references are passed in).
 * 2) HelloWorldAction, IndexAction (S2_StarterApp_1) - 1st line of execute() methods (to see what is returned from the container).
 * 3) CustomOECFactory (S2_StarterApp_1) - every method to see if any get called.
 */
public class CustomOECFactory extends DefaultOgnlExpressionCacheFactory<String, Object> {

    private static final Logger LOG = LogManager.getLogger(CustomOECFactory.class);

    public CustomOECFactory() {
        LOG.info("Custom expression cache factory has been created");
    }

    @Override
    public OgnlCache<String, Object> buildOgnlCache() {
        return buildOgnlCache(getCacheMaxSize(), 16, 0.75f, getUseLRUCache());
    }

    @Override
    public OgnlCache<String, Object> buildOgnlCache(int evictionLimit, int initialCapacity, float loadFactor, boolean lruCache) {
        if (lruCache) {
            return new CustomOELRUC(evictionLimit, initialCapacity, loadFactor);
        } else {
            return new CustomOEC(evictionLimit, initialCapacity, loadFactor);
        }
    }

}
