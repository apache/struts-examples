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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.text.StrutsLocalizedTextProvider;
import org.apache.struts2.util.finder.ResourceFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Expands entries such as {@code com.acme.messages.*} in {@code struts.custom.i18n.resources}
 * into every bundle placed directly in that package (sub-packages are not scanned).
 */
public class WildcardLocalizedTextProvider extends StrutsLocalizedTextProvider {

    private static final Logger LOG = LogManager.getLogger(WildcardLocalizedTextProvider.class);

    private static final String WILDCARD_SUFFIX = ".*";
    private static final String PROPERTIES_EXTENSION = ".properties";

    // No @Inject here: the container already calls this through the annotated parent method,
    // annotating the override as well would inject it twice
    @Override
    public void setCustomI18NResources(String bundles) {
        if (bundles == null || bundles.isEmpty()) {
            return;
        }
        List<String> expanded = new ArrayList<>();
        for (String name : bundles.split("[,\\s]+")) {
            if (name.endsWith(WILDCARD_SUFFIX)) {
                expanded.addAll(findBundlesInPackage(name.substring(0, name.length() - WILDCARD_SUFFIX.length())));
            } else if (!name.isEmpty()) {
                expanded.add(name);
            }
        }
        super.setCustomI18NResources(String.join(",", expanded));
    }

    /**
     * Bundles are returned sorted, so lookup order does not depend on how the container lists the classpath:
     * a bundle registered later is searched first, so on a duplicated key the last name in sorted order wins.
     */
    private Set<String> findBundlesInPackage(String packageName) {
        Set<String> baseNames = new TreeSet<>();
        try {
            for (String fileName : new ResourceFinder("").getResourcesMap(packageName.replace('.', '/')).keySet()) {
                if (fileName.endsWith(PROPERTIES_EXTENSION)) {
                    baseNames.add(fileName.substring(0, fileName.length() - PROPERTIES_EXTENSION.length()));
                }
            }
        } catch (IOException e) {
            LOG.error("Could not scan package [{}] for message bundles. Skipping", packageName, e);
        }
        Set<String> bundles = new TreeSet<>();
        for (String baseName : baseNames) {
            if (!isLocaleVariant(baseName, baseNames)) {
                bundles.add(packageName + "." + baseName);
            }
        }
        if (bundles.isEmpty()) {
            LOG.warn("No message bundles found in package [{}], [{}{}] registers nothing", packageName, packageName, WILDCARD_SUFFIX);
        } else {
            LOG.debug("Expanded [{}{}] to bundles {}", packageName, WILDCARD_SUFFIX, bundles);
        }
        return bundles;
    }

    private static boolean isLocaleVariant(String name, Set<String> baseNames) {
        return baseNames.stream().anyMatch(base -> name.startsWith(base + "_"));
    }
}
