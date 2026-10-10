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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class WildcardLocalizedTextProviderTest {

    private static final Locale POLISH = Locale.forLanguageTag("pl");

    private Locale defaultLocale;
    private WildcardLocalizedTextProvider provider;

    @Before
    public void setUp() {
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
        provider = new WildcardLocalizedTextProvider();
        provider.setCustomI18NResources("DefaultMessages,org.apache.struts_example.messages.*");
    }

    @After
    public void tearDown() {
        Locale.setDefault(defaultLocale);
    }

    @Test
    public void wildcardRegistersEveryBundleInThePackage() {
        assertEquals("Hello from the common bundle", provider.findDefaultText("common.greeting", Locale.ENGLISH));
        assertEquals("Loaded from the labels bundle via a wildcard", provider.findDefaultText("labels.title", Locale.ENGLISH));
    }

    @Test
    public void localeVariantsResolveThroughTheirBaseBundle() {
        assertEquals("Hello from the common bundle", provider.findDefaultText("common.greeting", Locale.ENGLISH));
        assertEquals("Witaj z pakietu common", provider.findDefaultText("common.greeting", POLISH));
    }

    @Test
    public void laterBundleInSortedOrderWinsOnDuplicateKey() {
        assertEquals("Defined in labels", provider.findDefaultText("shared.key", Locale.ENGLISH));
    }

    @Test
    public void namesWithoutWildcardPassThrough() {
        assertEquals("Default: Custom Text Provider example", provider.findDefaultText("default.index.title", Locale.ENGLISH));
    }
}
