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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <code>Set welcome message.</code>
 */
public class ProduceAction extends ActionSupport {

    private User user;

    public String execute() throws Exception {
        user = new User();

        user.setName("William Shakespeare");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        user.setBirthday(formatter.parse("26-Apr-1564"));

        List<Phone> phoneNumbers = new ArrayList<Phone>();
        Phone phone = new Phone();
        phone.setName("cell");
        phone.setNumber("555-123-4567");
        phoneNumbers.add(phone);
        phone = new Phone();
        phone.setName("home");
        phone.setNumber("555-987-6543");
        phoneNumbers.add(phone);
        phone = new Phone();
        phone.setName("work");
        phone.setNumber("555-678-3542");
        phoneNumbers.add(phone);
        user.setPhoneNumbers(phoneNumbers);

        List<Address> addresses = new ArrayList<Address>();
        Address address = new Address();
        address.setName("home");
        address.setCity("Stratford-upon-Avon");
        address.setStreet("Henley");
        List<Zipcode> zipcodes = new ArrayList<Zipcode>();
        Zipcode zipcode = new Zipcode();
        zipcode.setCode("CV37");
        zipcodes.add(zipcode);
        address.setZipcodes(zipcodes);
        addresses.add(address);
        user.setAddresses(addresses);

        user.setLogin("WillShak");
        user.setHashedPassword("9e107d9d372bb6826bd81d3542a419d6");
        user.setLastLogin(new Date());
        user.setPassword("will123shak456");

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }
}
