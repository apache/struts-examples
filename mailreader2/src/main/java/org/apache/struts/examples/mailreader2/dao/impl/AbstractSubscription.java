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


package org.apache.struts.examples.mailreader2.dao.impl;

import org.apache.struts.examples.mailreader2.dao.Subscription;
import org.apache.struts.examples.mailreader2.dao.User;

/**
 * <p>Concrete implementation of {@link AbstractSubscription}.</p>
 *
 * @version $Rev$
 * @since Struts 1.1
 */

public class AbstractSubscription implements Subscription {


    // ----------------------------------------------------------- Constructors


    /**
     * <p>Construct a new Subscription associated with the specified
     * {@link User}.
     *
     * @param user The user with which we are associated
     * @param host The mail host for this subscription
     */
    public AbstractSubscription(User user, String host) {

        super();
        this.user = user;
        this.host = host;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The mail host for this subscription.
     */
    private String host = null;


    /**
     * The {@link User} with which we are associated.
     */
    private User user = null;


    // ------------------------------------------------------------- Properties


    /**
     * Should we auto-connect at startup time?
     */
    private boolean autoConnect = false;

    public boolean getAutoConnect() {
        return (this.autoConnect);
    }

    public void setAutoConnect(boolean autoConnect) {
        this.autoConnect = autoConnect;
    }


    /**
     * The mail host for this subscription.
     */
    public String getHost() {
        return (this.host);
    }


    /**
     * The password (in clear text) for this subscription.
     */
    private String password = null;

    public String getPassword() {
        return (this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * The subscription type ("imap" or "pop3").
     */
    private String type = "imap";

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }


    /**
     * The User owning this Subscription.
     */
    public User getUser() {
        return (this.user);
    }


    /**
     * The username for this subscription.
     */
    private String username = null;

    public String getUsername() {
        return (this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("<subscription host=\"");
        sb.append(host);
        sb.append("\" autoConnect=\"");
        sb.append(autoConnect);
        sb.append("\"");
        if (password != null) {
            sb.append(" password=\"");
            sb.append(password);
            sb.append("\"");
        }
        if (type != null) {
            sb.append(" type=\"");
            sb.append(type);
            sb.append("\"");
        }
        if (username != null) {
            sb.append(" username=\"");
            sb.append(username);
            sb.append("\"");
        }
        sb.append(">");
        return (sb.toString());

    }


}