//$('#username').setCustomValidity('Your custom validation message comes here')

function checkTemporaryEmployee(contractType){
    if(contractType === '2'){
        $('#contract\\.endDate').prop('required',true);
    }else{
        $('#contract\\.endDate').removeAttr('required');
    }
}

// Tooltips Initialization
$('#username').blur(function(event) {
    event.target.checkValidity();
}).bind('invalid', function(event) {

    var $username = $('#username')

    if( !$username.val() ) {

        $username.attr("placeholder", "Este campo es obligatorio");

        $username.focus();
    }
});

$('#password').blur(function(event) {
    event.target.checkValidity();
}).bind('invalid', function(event) {

    var $password = $('#password')

    if( !$password.val() ) {

        var $user = $('#username')

        if(!$user.val()){

        }
        else{
            $password.attr("placeholder", "Este campo es obligatorio");

            $password.focus();
        }



    }
});

