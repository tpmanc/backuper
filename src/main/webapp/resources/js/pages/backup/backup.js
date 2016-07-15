$(function(){
    var deleteBackup = $('#deleteBackup');
    var runBackup = $('#runBackup');
    var body= $('body');

    $('.event-table').on('click', function(){
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
});