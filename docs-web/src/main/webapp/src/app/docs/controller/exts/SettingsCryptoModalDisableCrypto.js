'use strict';

/**
 * Settings modal disable Crypto controller.
 */
angular.module('docs').controller('SettingsCryptoModalDisableCrypto', function ($scope, $uibModalInstance) {
  $scope.password = '';
  $scope.close = function(password) {
    $uibModalInstance.close(password);
  }
});