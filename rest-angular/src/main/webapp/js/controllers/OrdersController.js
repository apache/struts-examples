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
        .controller('OrdersController', OrdersController);

    function OrdersController($log, DataService) {
        var vm = this;
        init();

        function init() {
            return DataService.getOrders().then(function(orders) {
                vm.orders = orders;
                return vm.orders;
            }, function() {
                $log.error('Could not receive list of orders.');
            });
        }

        vm.deleteOrder = function(order) {
            if (confirm("Are you sure you want to delete order "+ order.id +"?")) {
                DataService.deleteOrder(order.id).then(function() {
                    vm.orders.splice(vm.orders.indexOf(order), 1);
                }, function() {
                    $log.error('Could not delete order with id: '+ order.id);
                });
            }
        };
    }
})();
