'use strict';

$(function() {
    $('#confirmationPassword').on('blur',function() {
        let password = $('#password').val();
        let confirmationPassword = $(this).val();

        if(password !== confirmationPassword) {
            // パスワードが一致しない場合、警告文を表示
            $('#passwordErrorMessage').text('パスワードが一致しません');
        } else {
            // パスワードが一致する場合、警告文をクリア
            $('#passwordErrorMessage').text('');
        }
    });

    $('#password').on('blur',function() {
        let password = $(this).val();
        let confirmationPassword = $('#confirmationPassword').val();

        if(password !== confirmationPassword) {
            // パスワードが一致しない場合、警告文を表示
            $('#passwordErrorMessage').text('パスワードが一致しません');
        } else {
            // パスワードが一致する場合、警告文をクリア
            $('#passwordErrorMessage').text('');
        }
    });

});