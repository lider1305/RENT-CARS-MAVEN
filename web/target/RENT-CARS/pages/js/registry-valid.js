$(document).ready(function(){
// ====================================================== //

    var jVal = {
        'fullName' : function() {

            $('body').append('<div id="nameInfo" class="info"></div>');

            var nameInfo = $('#nameInfo');
            var ele = $('#fullName');
            var pos = ele.offset();

            nameInfo.css({
                top: pos.top-3,
                left: pos.left+ele.width()+15
            });

            if(ele.val().length < 3) {
                jVal.errors = true;
                nameInfo.removeClass('correct').addClass('error').html('&larr; вы не заполнили данное поле!').show();
                ele.removeClass('normal').addClass('wrong');
            } else {
                nameInfo.removeClass('error').addClass('correct').html('&radic;').show();
                ele.removeClass('wrong').addClass('normal');
            }
        }

       
    };

// ====================================================== //

    $('#send').click(function (){
        var obj = $.browser.webkit ? $('body') : $('html');
        obj.animate({ scrollTop: $('#r_form').offset().top }, 750, function (){
            jVal.errors = false;
            jVal.fullName();
           
        });
        return false;
    });

    $('#fullName').change(jVal.fullName);

// ====================================================== //
});
