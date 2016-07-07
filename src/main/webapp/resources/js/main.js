$(function(){
    $('.clickable-area').on('click', function(){
        $('#uploadBtn').click();
        return false;
    })
    $('#uploadBtn').on('change', function(){
        var value = this.files[0];
        if (value) {
            $('#image').val(value.name).closest('.mdl-textfield').addClass('is-dirty');

            var reader = new FileReader();
            reader.onload = function (e) {
                $('#imagePreview').attr('src', e.target.result);
            }
            reader.readAsDataURL(value);
        } else {
            $('#image').val('').closest('.mdl-textfield').removeClass('is-dirty');
        }
    });
});