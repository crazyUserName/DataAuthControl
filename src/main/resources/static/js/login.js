  $("#sub").click(function(){
	  
	  		var userName = $('#userName').val();
	  		if(userName == "" ){
	  			 $("#errorContext").html("<i class='ace-icon fa fa-exclamation-triangle red'></i>用户名不能为空");
	  			 return;
	  		}
	  		
	  		var password = $('#password').val();
	  		if(password == ""){
	  			 $("#errorContext").html("<i class='ace-icon fa fa-exclamation-triangle red'></i>密码不能为空");
	  			 return;
	  		}
	  		
	  		var rememberMe = false;
	  		
	  		if($('#rememberMe').is(':checked')) {
	  			rememberMe = true;
	  		}

		          var encrypt = new JSEncrypt();
		          encrypt.setPublicKey($('#publicKey').val());
		          var encrypted = encrypt.encrypt(password);
		          
		          var data = {
		        		  userName : userName,
		        		  
		        		  password : encrypted,
		        		  rememberMe:rememberMe
		          };
		          
				 $.post("/user/login", data, function(result){
					 
					 if(result.flag){
						 window.location.href = "/form/goList";
					 }else{
						 $("#errorContext").html("<i class='ace-icon fa fa-exclamation-triangle red'></i>" + result.msg);
					 }
				 });
			 }); 