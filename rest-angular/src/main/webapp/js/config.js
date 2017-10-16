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
        .config(['$routeProvider', '$locationProvider', '$translateProvider',
            function($routeProvider, $locationProvider, $translateProvider) {

                // Configuration for translation provider
                $translateProvider.registerAvailableLanguageKeys(['en', 'de']);
                $translateProvider.fallbackLanguage('en');
                $translateProvider.useUrlLoader('data/language.json', {
                    queryParameter: 'request_locale'
                });
                $translateProvider.useSanitizeValueStrategy('escaped');
                $translateProvider.determinePreferredLanguage();

                // Configuration for html5 based URIs
                $locationProvider.html5Mode(true).hashPrefix('!');

                // Route mapping for URL, template and controller
                $routeProvider.when('/orders', {
                    templateUrl: 'partials/orders.html',
                    controller: 'OrdersController as vm'
                }).when('/order/new', {
                    templateUrl: 'partials/order-form.html',
                    controller: 'OrderAddController as vm'
                }).when('/order/:id', {
                    templateUrl: 'partials/order-detail.html',
                    controller: 'OrderDetailController as vm'
                }).when('/order/:id/edit', {
                    templateUrl: 'partials/order-form.html',
                    controller: 'OrderEditController as vm'
                }).otherwise({ redirectTo: '/orders' });
            }
        ]);
})();
