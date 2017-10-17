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
(function() {
    'use strict';

    angular
        .module('app')
        .factory('DataService', DataService);

    function DataService($http, $log, $q, $rootScope) {

        /** Object to manage all backend URL's */
        var urls = {
                orders : "data/order",
                order : "data/order/"
            },
            suffix = ".json";

        /** The DataService with all public methods */
        var service = {
            getOrders: getOrders,
            getOrder: getOrder,
            editOrder: editOrder,
            addOrder: addOrder,
            deleteOrder: deleteOrder
        };

        return service;

        /** Get all orders */
        function getOrders() {
            return _request(urls.orders);
        }

        /** Get order by id */
        function getOrder(id) {
            return _request(urls.order+id);
        }

        /** Add new order */
        function addOrder(order) {
            return _request(urls.order, 'POST', order);
        }

        /** Edit order */
        function editOrder(order) {
            return _request(urls.order+order.id, 'PUT', order);
        }

        /** Delete order by id */
        function deleteOrder(id) {
            return _request(urls.order+id, 'DELETE');
        }


        /** A generic helper method to execute a HTTP request to the backend */
        function _request(url, method, model){
            var def = $q.defer(),
                req = {
                    method: method,
                    url: url +suffix
                };

            if(!method) {
                req.method = 'GET';
            }

            if(model) {
                req.data = model;
            }
            $http(req).success(function(data) {
                def.resolve(data);
            }).error(function(data, code) {
                def.reject(data);
                if(data.actionError) {
                    $rootScope.$emit('data-error', {  msg: data.actionError });
                }
                $log.error(data, code);
            });
            return def.promise;
        }
    }

})();
