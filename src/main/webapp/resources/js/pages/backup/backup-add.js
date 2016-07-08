$(function(){
    var backupType = $('input[type="radio"][name="backupType"]');
    var databaseType = $('input[type="radio"][name="databaseType"]');
    var databaseSettings = $('#databaseSettings');
    var filesSettings = $('#filesSettings');

    backupType.on('change', function(){
        var backupTypeValue = this.value;
        if (backupTypeValue == 1) {
            databaseSettings.show();
            filesSettings.hide();
        } else if (backupTypeValue == 2) {
            databaseSettings.hide();
            filesSettings.show();
        }
    });
});