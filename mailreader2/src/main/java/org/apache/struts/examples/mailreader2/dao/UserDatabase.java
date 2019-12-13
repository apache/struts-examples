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
 * <p>A <strong>Data Access Object</strong> (DAO) interface describing
 * the available operations for retrieving and storing {@link User}s
 * (and their associated {@link Subscription}s) in some persistence layer
 * whose characteristics are not specified here.  One or more implementations
 * will be created to perform the actual I/O that is required.</p>
 */
public interface UserDatabase {

    // --------------------------------------------------------- Public Methods

    /**
     * <p>Create and return a new {@link User} defined in this user database.
     * </p>
     *
     * @param username Username of the new user
     *
     * @exception IllegalArgumentException if the specified username
     *  is not unique
     */
    User createUser(String username);

    /**
     * <p>Finalize access to the underlying persistence layer.</p>
     *
     * @exception Exception if a database access error occurs
     */
    void close() throws Exception;

    /**
     * <p>Return the existing {@link User} with the specified username,
     * if any; otherwise return <code>null</code>.</p>
     *
     * @param username Username of the user to retrieve
     * @throws ExpiredPasswordException if user password has expired
     * and must be changed
     */
    User findUser(String username) throws ExpiredPasswordException;

    /**
     * <p>Return the set of {@link User}s defined in this user database.</p>
     */
    User[] findUsers();

    /**
     * <p>Return true if open() has been called.</p>
     *
     * @exception Exception if a database access error occurs
     */
    boolean isOpen();

    /**
     * <p>Initiate access to the underlying persistence layer.</p>
     *
     * @exception Exception if a database access error occurs
     */
    void open() throws Exception;

    /**
     * Remove the specified {@link User} from this database.
     *
     * @param user User to be removed
     *
     * @exception IllegalArgumentException if the specified user is not
     *  associated with this database
     */
    void removeUser(User user);

    /**
     * <p>Save any pending changes to the underlying persistence layer.</p>
     *
     * @exception Exception if a database access error occurs
     */
    void save() throws Exception;

}