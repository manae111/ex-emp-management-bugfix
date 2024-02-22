'use strict';



// このスクリプトは、従業員の名前を検索するためのオートコンプリート機能を提供します
$.ajax({
    url: 'http://localhost:8080/employee/names', // 従業員の名前を返すエンドポイント
    method: 'GET',
    dataType: 'json',
    success: function(data) {
        // dataはサーバーから返されたJSONデータ
        // この例では、従業員の名前の配列を想定しています
        var availableTags = data;

        $( "#searchName" ).autocomplete({
            source: availableTags
        });
    },
    error: function(error) {
        console.log(error);
    }
});