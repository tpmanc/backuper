$(function(){
    var deleteBackup = $('#deleteBackup');
    var runBackup = $('#runBackup');
    var backupTable = $('#backupTable');
    var body= $('body');

    backupTable.on('click', function(){
        var table = $(this);
        setTimeout(function(){
            var selectedCount = table.find('tr.is-selected').length;
            if (selectedCount > 0) {
                deleteBackup.removeClass('delete-btn-hidden');
                runBackup.addClass('run-backup-btn-hidden');
            } else {
                deleteBackup.addClass('delete-btn-hidden');
                runBackup.removeClass('run-backup-btn-hidden');
            }
        }, 200);
    });

    deleteBackup.on('click', function(){
        var selected = backupTable.find('tr.is-selected');
        if (selected.length > 0) {
            var idArr = [];
            selected.each(function(i, e){
                idArr.push($(e).data('id'));
            });
            var headers = {};
            headers['X-CSRF-TOKEN'] = csrfToken;
            $.ajax({
                type: "POST",
                url: deleteUrl,
                dataType: 'json',
                headers: headers,
                data: {'id': idArr},
                success: function(){}

            });
        }
    });
});