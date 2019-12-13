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

package org.apache.struts.examples.mailreader2.dao;

/**
 * <p>A <strong>Subscription</strong> which is stored, along with the
 * associated {@link User}, in a {@link UserDatabase}.</p>
 *
 * @version $Rev$ $Date$
 */
public interface Subscription {

    // ------------------------------------------------------------- Properties

    /**
     * Return the auto-connect flag.
     */
    boolean getAutoConnect();

    /**
     * Set the auto-connect flag.
     *
     * @param autoConnect The new auto-connect flag
     */
    void setAutoConnect(boolean autoConnect);

    /**
     * Return the host name.
     */
    String getHost();

    /**
     * Return the password.
     */
    String getPassword();

    /**
     * Set the password.
     *
     * @param password The new password
     */
    void setPassword(String password);

    /**
     * Return the subscription type.
     */
    String getType();

    /**
     * Set the subscription type.
     *
     * @param type The new subscription type
     */
    void setType(String type);

    /**
     * Return the {@link User} owning this Subscription.
     */
    User getUser();

    /**
     * Return the username.
     */
    String getUsername();

    /**
     * Set the username.
     *
     * @param username The new username
     */
    void setUsername(String username);

}
