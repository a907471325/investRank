function checkUsername() {
    var username = $("#loginName").val();
    if((/^1[34578]\d{9}$/.test(username))||(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(username))){
        clearTip();
    }
    else{
        $("#loginTips").css('color','red').html('请填写正确的邮箱或手机号') ;
    }

}

function checkPassword() {
    var password = $("#password").val();
    if(password === '' ){
        $("#loginTips").css('color','red').html('密码不能为空');
    }
    else if(!/^[a-zA-z0-9]{6,15}$/.test(password)){
        $("#loginTips").css('color','red').html('密码格式位6-15位数字或字母');
    }
    else {
        clearTip();
        checkUsername();
    }

}

var clearTip = function () {
    var tip = $("#loginTips").html();
    if (tip != '') $("#loginTips").html('');
};


$("#loginName").change(checkUsername);
$("#password").on('change',checkPassword);

function login() {
    $("#loginForm").attr('action','/index');
    $("#loginForm").submit();
}
function loginSearch() {
    var param = $(".input-search").val();
    $("#loginForm").attr('action','/search?areaName='+param);
    $("#loginForm").submit();

}
