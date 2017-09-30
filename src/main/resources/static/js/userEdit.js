function updateInit(){
	$('#updateUserForm').bootstrapValidator({
//        live: 'disabled',
        message: '对应值无效',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            password: {
                message:'密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度必须在6到30之间'
                    },
                    different: {//不能和用户名相同
                        field: 'username1',//需要进行比较的input name值
                        message: '不能和用户名相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: 'The username can only consist of alphabetical, number, dot and underscore'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮件不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    }
                }
            },
            realName: {
                validators: {
                    notEmpty: {
                        message: '真实姓名不能为空'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位手机号码'
                    },
                    regexp: {
                        regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                        message: '请输入正确的手机号码'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {//点击提交之后
            e.preventDefault();
            
            var password = $('#password').val();
            var encrypt = new JSEncrypt();
            encrypt.setPublicKey($('#publicKey').val());
            var encrypted = encrypt.encrypt(password);
            
            var data = {
            	  id :  $('#id').val(),
          		  userName :  $('#userName').val(),
          		  password : encrypted,
          		  email : $('#email').val(),
          		realName : $('#realName').val(),
          		phone : $('#phone').val(),
          		roleIds :$('#roleIds').val().join(","),
          		comment : $('#comment').val()
            };
            $.post("/user/update", data, function(result) {
            	window.location.href = "/user/goList";
		   });
        }); 
	 }