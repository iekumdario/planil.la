//$('#username').setCustomValidity('Your custom validation message comes here')

function checkTemporaryEmployee(contractType){
    if(contractType === '2'){
        $('#contract\\.endDate').prop('required',true);
    }else{
        $('#contract\\.endDate').removeAttr('required');
    }
}