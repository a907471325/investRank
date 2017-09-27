$( function() {
    var browser_width = $(document.body).width();
    $("body").css("width",browser_width);

    $("#afterLogin").css('display','inline');
    $("#exitIcon").on('click',function () {
        /*注销*/
        window.location.href="/logout";
    });

    $("#input").attr('spellcheck',false);

    $(".center-body-left li").on('click',function () {
        var linum = $(".center-body-left li").index(this);
        //var len = $(".center-body-left li").length;
        //var blocklen = $(".show-divs").length;

        $(".center-body-left li").css('backgroundColor', 'rgba(0,0,0,0)');
        $(".show-divs").css('display','none');
        if(linum > 3 || linum === 0)
            $(".show-divs").eq(linum).css('display','inline');
        else{
            $(".show-divs").eq(linum).css('display','inline');
            var flag = $(".checkFlag").eq(linum-1).val();
            //console.log(linum);
            //console.log($(".checkFlag").length);
            //console.log(flag);
            if(flag !=null && flag != ''){
                $(".change-bind").eq(linum-1).css('display','inline');
            }
            else {
                $(".not-bind").eq(linum-1).css('display','inline');
            }
        }

        $(".center-body-left li").eq(linum).css('backgroundColor','#2680D9');
    });

    function checkOriPassword() {
        var password = $("#oriPassword").val();
        if(password === ''){
            $("#passwordTips").css('color','red').html('密码不能为空');
        }
        else if(!/^[a-zA-z0-9]{6,15}$/.test(password)){
            $("#passwordTips").css('color','red').html('密码格式位6-15位数字或字母');
        }
        else{
            clearPasswordTip();
        }

    }

    function checkNewPassword1st() {
        var password = $("#newPassword1st").val();
        if(password === '' ){
            $("#passwordTips").css('color','red').html('密码不能为空');
        }
        else if(!/^[a-zA-z0-9]{6,15}$/.test(password)){
            $("#passwordTips").css('color','red').html('密码格式位6-15位数字或字母');
        }
        else{
            clearPasswordTip();
        }

    }
    function checkNewPassword2nd() {
        var password = $("#newPassword2nd").val();
        if(password === ''){
            $("#passwordTips").css('color','red').html('密码不能为空');
        }
        else if(!/^[a-zA-z0-9]{6,15}$/.test(password)){
            $("#passwordTips").css('color','red').html('密码格式位6-15位数字或字母');
        }
        else if(password != $("#newPassword1st").val()){
            $("#passwordTips").css('color','red').html('两次密码输入不一致');
        }
        else clearPasswordTip();

    }

    function clearPasswordTip() {
        var tip = $("#passwordTips").html();
        if (tip != '') $("#passwordTips").html('');
    }

    $("#oriPassword").change(checkOriPassword);
    $("#newPassword1st").on('change',checkNewPassword1st);
    $("#newPassword2nd").on('change',checkNewPassword2nd);

    $("#submitpwd").on('click',function () {
        checkOriPassword();
        checkNewPassword1st();
        checkNewPassword2nd();
        if ($("#passwordTips").html() === ''){
            $.ajax({
                type:'POST',
                dataType: "text",
                url : '/changePassword',
                async : true,
                data : {'username':$('#username').html(),
                        'oriPassword':$('#oriPassword').val(),
                        'newPassword':$('#newPassword1st').val()
                },
                success : function(rst){
                    $('#oriPassword').val('');
                    $('#newPassword1st').val('');
                    $('#newPassword2nd').val('');
                    if(rst === 'input'){
                        var txt=  "原密码输入错误！";
                        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                    }
                    if(rst === 'success') {
                        var txt=  "密码修改成功！";
                        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
                    }
                }
            })
        }
    })

    //添加新的Email

    function checkEmail() {
        var email = $("#setEmail").val();
        if((/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(email)))
        {
            clearSetEmailTip();
        }
        else{
            $("#setEmailTips").css('color','red').html('请填写正确的邮箱') ;
        }

    }

    function checkEmailVercode() {
        var param = '';
        var code = $("#emailVercode").val();
        $.ajax({
            type : 'get',
            dataType : "text",
            async : false,
            url : '/getCodeSession',
            data : {'date':new Date().getTime()},
            success : function(rst){
                if(rst != '') {
                    param = rst;
                }
            }
        });
        if(code === ''){
            $("#setEmailTips").css('color','red').html('请输入验证码');
        }
        else if (code.toUpperCase() != param){
            $("#setEmailTips").css('color','red').html('验证码错误');
        }else {
            clearSetEmailTip();
            checkEmail();
        }

    }

    var clearSetEmailTip = function () {
        var tip = $("#setEmailTips").html();
        if (tip != '') $("#setEmailTips").html('');
    };


    $("#setEmail").change(checkEmail);
    $("#emailVercode").on('change',checkEmailVercode);

    $("#submitEmail").on('click',function(){
        checkEmail();
        checkEmailVercode();
        if ($("#setEmailTips").html() === ''){
            $.ajax({
                type:'POST',
                dataType: "text",
                url : '/addEmail',
                async : true,
                data : {'username':$('#username').html(),
                    'oriPassword':$('#passwordBack').val(),
                    'oriEmail':$('#userId').val(),
                    'newEmail':$('#setEmail').val()
                },
                success : function(rst){
                    $('#setEmail').val('');
                    $('#emailVercode').val('');
                    if(rst === 'input'){
                        var txt=  "该邮箱已经被注册！";
                        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                    }
                    if(rst === 'success') {
                        $("body").append("<div id='mask'></div>");
                        $("#mask").addClass("mask").fadeIn("slow");
                        $("#confirmEmail").fadeIn("slow");
                    }
                    if(rst === 'error'){
                        var txt=  "邮件发送失败,请重新发送！";
                        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                    }
                }
            })
        };
    });

    //修改Email

    function checkOriEmail() {
        var email = $("#oriEmail").val();
        if((/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(email)))
        {
            clearSetNewEmailTip();
        }
        else{
            $("#setNewEmailTips").css('color','red').html('请填写正确的邮箱') ;
        }

    }

    function checkNewEmail() {
        var email = $("#newEmail").val();
        if((/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(email)))
        {
            clearSetNewEmailTip();
        }
        else{
            $("#setNewEmailTips").css('color','red').html('请填写正确的邮箱') ;
        }

    }

    var clearSetNewEmailTip = function () {
        var tip = $("#setNewEmailTips").html();
        if (tip != '') $("#setNewEmailTips").html('');
    };


    $("#oriEmail").change(checkOriEmail);
    $("#newEmail").on('change',checkNewEmail);

    $("#submitNewEmail").on('click',function(){
        checkOriEmail();
        checkNewEmail();
        if ($("#setNewEmailTips").html() === ''){
            $.ajax({
                type:'POST',
                dataType: "text",
                url : '/updateEmail',
                async : true,
                data : {
                    'oriEmail':$('#oriEmail').val(),
                    'newEmail':$('#newEmail').val(),
                    'username':$('#userId').val()
                },
                success : function(rst){
                    $('#newEmail').val('');
                    $('#oriEmail').val('');
                    if(rst === 'input'){
                        var txt=  "原邮箱输入错误！";
                        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                    }
                    if(rst === 'registed'){
                        var txt=  "该邮箱已经被注册！";
                        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                    }
                    if(rst === 'success') {
                        $("body").append("<div id='mask'></div>");
                        $("#mask").addClass("mask").fadeIn("slow");
                        $("#confirmEmail").fadeIn("slow");
                    }
                    if(rst === 'error'){
                        txt=  "邮件发送失败,请重新发送！";
                        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                    }
                }
            })
        };
    });
    /*$("#submitEmail").on('click',function(){
        $("body").append("<div id='mask'></div>");
        $("#mask").addClass("mask").fadeIn("slow");
        $("#confirmEmail").fadeIn("slow");
    });*/

    /*$("#submitNewEmail").on('click',function(){
        $("body").append("<div id='mask'></div>");
        $("#mask").addClass("mask").fadeIn("slow");
        $("#confirmEmail").fadeIn("slow");
    });*/

    $("#closeBtn5").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#confirmEmail").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });

    $("#closeBtn6").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#confirmWx").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });

    $("#closeBtn7").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#confirmWx").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });

    $("#IKnow").on('click',function () {
        $("#closeBtn5").click();
    });

    $("#submitNewWeixin").on('click',function(){
        $("body").append("<div id='mask'></div>");
        $("#mask").addClass("mask").fadeIn("slow");
        $("#confirmWx").fadeIn("slow");
    })

    $('#changeVercode').on('click',function() {
        document.getElementById('imagecode').src='/getVercode?d='+new Date().getTime();
    });

} );