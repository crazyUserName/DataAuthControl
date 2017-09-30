$("#formFieldValueBar").addClass("active");

function append(data) {
    var fjs='';  
    for(var i=0;i<data.length;i++)  
    {  
        fjs += '<option value='+data[i].id+'>' + data[i].tagKey+ '</option>';  
    }  
    return fjs;  
}

function formChange() {
	var value = $("#formId")[0].selectedOptions[0].value;
	$.ajax({
        type: "POST",
        url: "/formField/findByFormId/"+value,
        success: function(data){
        	if(data.flag){
        		$("#fieldId").html(append(data.data));  
        	}
        }
    });
}
	
function goList() {
	window.location.href = "/formFieldValue/goList";
}