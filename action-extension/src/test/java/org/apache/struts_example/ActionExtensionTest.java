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

import org.apache.struts2.ActionSupport;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.junit.StrutsJUnit4TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ActionExtensionTest extends StrutsJUnit4TestCase<Object> {

    @Test
    public void testGlobalExtensionMapsHello() {
        ActionMapping mapping = getActionMapping("/hello.action");

        assertNotNull(mapping);
        assertEquals("hello", mapping.getName());
        assertEquals("/", mapping.getNamespace());
        assertEquals("action", mapping.getExtension());
    }

    @Test
    public void testPackageExtensionMapsOrders() {
        ActionMapping mapping = getActionMapping("/legacy/orders.do");

        assertNotNull(mapping);
        assertEquals("orders", mapping.getName());
        assertEquals("/legacy", mapping.getNamespace());
        assertEquals("do", mapping.getExtension());
    }

    @Test
    public void testActionExtensionMapsReport() {
        ActionMapping mapping = getActionMapping("/legacy/report");

        assertNotNull(mapping);
        assertEquals("report", mapping.getName());
        assertEquals("/legacy", mapping.getNamespace());
    }

    @Test
    public void testUndeclaredActionDoesNotClaimDo() {
        assertNull(getActionMapping("/hello.do"));
    }

    @Test
    public void testHelloExecutesOnAction() throws Exception {
        assertEquals(ActionSupport.SUCCESS, getActionProxy("/hello.action").execute());
    }

    @Test
    public void testOrdersExecutesOnDo() throws Exception {
        assertEquals(ActionSupport.SUCCESS, getActionProxy("/legacy/orders.do").execute());
    }

    @Test
    public void testReportExecutesWithoutExtension() throws Exception {
        assertEquals(ActionSupport.SUCCESS, getActionProxy("/legacy/report").execute());
    }

    @Test
    public void testOrdersRejectsActionExtension() throws Exception {
        assertEquals("bad-extension", getActionProxy("/legacy/orders.action").execute());
    }

    @Test
    public void testReportRejectsDoExtension() throws Exception {
        assertEquals("bad-extension", getActionProxy("/legacy/report.do").execute());
    }
}
