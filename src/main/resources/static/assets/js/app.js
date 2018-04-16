//$('#username').setCustomValidity('Your custom validation message comes here')

function checkTemporaryEmployee(contractType){
    if(contractType === '2'){
        $('#contract\\.endDate').prop('required',true);
    }else{
        $('#contract\\.endDate').removeAttr('required');
    }
}

function deleteEmployee(formId){
    var modalElement = $('#confirmDeleteModal');
    modalElement.find('#titleText').first().html('Eliminar colaborador');
    modalElement.find('#message').first().html('Esta seguro que desea eliminar a este colaborador? No podra; deshacer esta accion');
    modalElement.find('#confirmDeleteButton').first().attr('onclick', 'document.getElementById(\''+formId+'\').submit();');
    modalElement.modal();
}

function deleteCreditor(formId){
    var modalElement = $('#confirmDeleteModal');
    modalElement.find('#titleText').first().html('Eliminar acreedor');
    modalElement.find('#message').first().html('Estas seguro que deseas eliminar este acreedor?');
    modalElement.find('#confirmDeleteButton').first().attr('onclick', 'document.getElementById(\''+formId+'\').submit();');
    modalElement.modal();
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

// Que hacia esto aqui?
// .w-auto {
//     width: auto;
// }
