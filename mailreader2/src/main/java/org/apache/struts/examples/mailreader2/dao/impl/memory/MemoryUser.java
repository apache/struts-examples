/*
 * $Id: $
 *
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

package org.apache.struts.examples.mailreader2.dao.impl.memory;

import org.apache.struts.examples.mailreader2.dao.UserDatabase;
import org.apache.struts.examples.mailreader2.dao.impl.AbstractUser;

/**
 * <p>Concrete implementation of {@link AbstractUser} used for an in-memory
 * database backed by an XML data file.</p>
 *
 * @version $Rev$
 */
public class MemoryUser extends AbstractUser {

    public MemoryUser(UserDatabase database, String username) {
        super(database, username);
    }
    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("<user username=\"");
        sb.append(getUsername());
        sb.append("\"");
        if (getFromAddress() != null) {
            sb.append(" fromAddress=\"");
            sb.append(getFromAddress());
            sb.append("\"");
        }
        if (getFullName() != null) {
            sb.append(" fullName=\"");
            sb.append(getFullName());
            sb.append("\"");
        }
        if (getPassword() != null) {
            sb.append(" password=\"");
            sb.append(getPassword());
            sb.append("\"");
        }
        if (getReplyToAddress() != null) {
            sb.append(" replyToAddress=\"");
            sb.append(getReplyToAddress());
            sb.append("\"");
        }
        sb.append(">");
        return (sb.toString());

    }

}