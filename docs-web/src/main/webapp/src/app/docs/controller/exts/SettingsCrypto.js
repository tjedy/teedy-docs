'use strict';

/**
 * Settings Crypto controller.
 */
angular.module('docs').controller('SettingsCrypto', function($scope, User, $dialog, $uibModal, Restangular, $translate) {
  User.userInfo().then(function(data) {
    $scope.user = data;
  });

  /**
   * Enable Crypto Wallet
   */
  $scope.enableCrypto = function () {
    var title = $translate.instant('exts.settings.crypto.enable_crypto');
    var msg = $translate.instant('exts.settings.crypto.enable_crypto_message');
    var btns = [
      { result:'cancel', label: $translate.instant('cancel') },
      { result:'ok', label: $translate.instant('ok'), cssClass: 'btn-primary' }
    ];

    $dialog.messageBox(title, msg, btns, function(result) {
      if (result === 'ok') {
        Restangular.one('user/enable_crypto').post().then(function(data) {
          $scope.secret = data.secret;
          User.userInfo(true).then(function(data) {
            $scope.user = data;
          })
        });
      }
    });
  };

  /**
   * Disable Crypto
   */
  $scope.disableCrypto = function () {
    $uibModal.open({
      templateUrl: 'partial/docs/exts/settings.crypto.disablecrypto.html',
      controller: 'SettingsCryptoModalDisableCrypto'
    }).result.then(function (password) {
      if (password === null) {
        return;
      }

      // Disable Crypto
      Restangular.one('user/disable_crypto').post('', {
        password: password
      }).then(function() {
        User.userInfo(true).then(function(data) {
          $scope.user = data;
        })
      });
    });
  };

  /**
   * Test Crypto.
   */
  $scope.testValidationCodeSuccess = null;
  $scope.testTotp = function (code) {
    Restangular.one('user/test_totp').post('', {
      code: code
    }).then(function() {
      $scope.testValidationCodeSuccess = true;
    }, function () {
      $scope.testValidationCodeSuccess = false;
    });
  };
});