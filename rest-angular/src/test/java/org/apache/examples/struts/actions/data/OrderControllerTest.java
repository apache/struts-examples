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
package org.apache.examples.struts.actions.data;

import com.jayway.jsonpath.JsonPath;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.examples.struts.models.Order;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.junit.StrutsRestTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class OrderControllerTest extends StrutsRestTestCase<OrderController> {

    @Before
    public void init() throws Exception {
        super.setUp();
    }

    @Test
    public void testIndex() throws Exception {
        ActionProxy proxy = getActionProxy("GET", "/data/order.json");
        proxy.execute();
        OrderController orderController = (OrderController) proxy.getAction();

        Object model = orderController.getModel();
        assertThat(model, notNullValue());
        assertThat(model, is(instanceOf(List.class)));

        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) model;

        assertThat("List contains 3 orders", orders.size(), is(greaterThanOrEqualTo(3)));
    }

    @Test
    public void testShow() throws Exception {
        ActionProxy proxy = getActionProxy("GET", "/data/order/3.json");
        OrderController orderController = (OrderController) proxy.getAction();
        orderController.setId("3");
        proxy.execute();

        Object model = orderController.getModel();
        assertThat(model, notNullValue());
        assertThat(model, is(instanceOf(Order.class)));

        Order order = (Order) model;
        assertThat(order.getClientName(), is(equalTo("Bob")));
        assertThat(order.getAmount(), is(equalTo(33)));
    }

    @Test
    public void testShowResult() throws Exception {
        String result = executeAction("GET", "/data/order/3.json");
        assertThat(JsonPath.read(result, "$.id"), is(equalTo("3")));
        assertThat(JsonPath.read(result, "$.clientName"), is(equalTo("Bob")));
        assertThat(JsonPath.read(result, "$.amount"), is(equalTo(33)));
    }

    @Test
    public void testDelete() throws Exception {
        request.setParameter("request_locale", "en");
        ActionProxy proxy = getActionProxy("DELETE", "/data/order/3.json");
        String result = proxy.execute();
        assertThat(result, nullValue());
        assertThat(ServletActionContext.getResponse().getContentType(), is(equalTo("application/json;charset=UTF-8")));
        String jsonResult = ((MockHttpServletResponse) ServletActionContext.getResponse()).getContentAsString();
        assertThat(JsonPath.read(jsonResult, "$.actionError"), is(equalTo("Delete is currently not supported!")));
        assertThat(ServletActionContext.getResponse().getStatus(), is(equalTo(400)));
    }

    @Test
    public void testValidation() throws Exception {
        request.setParameter("clientName", "");
        request.setParameter("amount", "1");
        request.setParameter("request_locale", "en");
        ActionProxy proxy = getActionProxy("POST", "/data/order.json");
        OrderController orderController = (OrderController) proxy.getAction();

        proxy.execute();

        assertThat(ServletActionContext.getResponse().getStatus(), is(equalTo(406)));
        assertThat(orderController.hasFieldErrors(), is(true));
        assertThat(orderController.getFieldErrors().size(), is(2));
        assertThat(orderController.getFieldErrors().get("amount").get(0), is(equalTo("Order amount needs to be between 10 and 666")));
        assertThat(orderController.getFieldErrors().get("clientName").get(0), is(equalTo("Client name can not be blank")));
    }

    @Test
    public void testCreate() throws Exception {
        request.setParameter("clientName", "Grace Hopper");
        request.setParameter("amount", "85");
        ActionProxy proxy = getActionProxy("POST", "/data/order.json");
        OrderController orderController = (OrderController) proxy.getAction();

        proxy.execute();

        assertThat(orderController.hasFieldErrors(), is(false));

        Object model = orderController.getModel();
        assertThat(model, notNullValue());
        assertThat(model, is(instanceOf(Order.class)));

        Order order = (Order) model;
        assertThat(order.getClientName(), is(equalTo("Grace Hopper")));
        assertThat(order.getAmount(), is(equalTo(85)));
    }
}
