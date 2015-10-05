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
    .controller('AppController', AppController);

    function AppController($rootScope, $translate) {
        var vm = this;

        // set the current selected language in root scope
        $rootScope.lang = $translate.proposedLanguage();

        vm.alerts = [];

        vm.closeAlert = function(index) {
            vm.alerts.splice(index, 1);
        };

        vm.switchLanguage = function(lang) {
            $rootScope.lang = lang;
            $translate.use(lang);

            // Refresh languages to set locale in backend
            $translate.refresh(lang);
        };

        $rootScope.$on('data-error', function(event, alert) {
            vm.alerts.push({type: 'danger', msg: alert.msg});
        });
        $rootScope.$on('data-success', function(event, alert) {
            vm.alerts.push({type: 'success', msg: alert.msg});
        });
    }
})();