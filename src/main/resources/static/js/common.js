function checkUsername(){var a=$("#loginName").val();/^1[34578]\d{9}$/.test(a)||/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(a)?clearTip():$("#loginTips").css("color","red").html("请填写正确的邮箱或手机号")}function checkPassword(){var a=$("#password").val();""===a?$("#loginTips").css("color","red").html("密码不能为空"):/^[a-zA-z0-9]{6,15}$/.test(a)?(clearTip(),checkUsername()):$("#loginTips").css("color","red").html("密码格式位6-15位数字或字母")}function login(){$("#loginForm").attr("action","/index"),$("#loginForm").submit()}function loginSearch(){var a=$(".input-search").val();$("#loginForm").attr("action","/search?areaName="+a),$("#loginForm").submit()}var clearTip=function(){""!=$("#loginTips").html()&&$("#loginTips").html("")};$("#loginName").change(checkUsername),$("#password").on("change",checkPassword);