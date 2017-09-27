$( function() {
    var browser_width = $(document.body).width();
    $("body").css("width",browser_width);

    $(".login").on('click',function(){
        $("body").append("<div id='mask'></div>");
        $("#mask").addClass("mask").fadeIn("slow");
        $("#loginAsWeixin").fadeIn("slow");
    });

    $(".regist").on('click',function(){
        $("body").append("<div id='mask'></div>");
        $("#mask").addClass("mask").fadeIn("slow");
        $("#registAsWeixin").fadeIn("slow");
    });
    $("#login").on('click',function () {
        checkPassword();
        var tip = $("#loginTips").html();
        if (tip === ''){
            $.ajax({
                type:'post',
                dataType: "json",
                url : '/loginCheck',
                async : false,
                data : {'loginname':$('#loginName').val(),'password':$('#password').val()},
                success : function(user){
                    if(user.loginname === null) $("#loginTips").css('color','red').html('用户名或密码错误');
                    else login();

                }
            })
        }
        //$("#closeBtn").click();
        //$(".header_inner_right button").css('display','none');
        //$("#afterLogin").css('display','inline');
    });
    $("#jump-regist").on('click',function () {
        $('#registAsWeixin').css('display','none');
        $('#registBox').css('display','inline');
    });
    $("#jump-login").on('click',function () {
        $('#loginAsWeixin').css('display','none');
        $('#loginBox').css('display','inline');
    });
    $("#exitIcon").on('click',function () {
        /*注销*/
        window.location.href = '/logout';
        //$("#afterLogin").css('display','none');
        //$(".header_inner_right button").css('display','inline');
    });
    $("#closeBtn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#loginBox").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });
    $("#closeBtn2").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#registBox").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });
    $("#closeBtn3").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#registAsWeixin").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });
    $("#closeBtn4").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#loginAsWeixin").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });

    $("#choosephone").on('click',function(){
        $(".regist-email-div").css({display:'none'});
        $("#chooseemail").css({textDecoration:'none'});
        $(this).css({textDecoration:'underline'});
        $(".regist-phone-div").css({display:'inline'});

    });
    $("#chooseemail").on('click',function(){
        $(".regist-phone-div").css({display:'none'});
        $("#choosephone").css({textDecoration:'none'});
        $(this).css({textDecoration:'underline'});
        $(".regist-email-div").css({display:'inline'});

    });

    $("#select_area").on('click',function () {
        $("#select_dev_area").css({borderBottom:'none'});
        $("#select_area").css({borderBottom:'3px solid #5f3f3f'});
        $(".input-search").attr('placeholder','请输入地域名,例如:北京,信阳,哈尔滨');
    });

    $("#select_dev_area").on('click',function () {
        $("#select_area").css({borderBottom:'none'});
        $("#select_dev_area").css({borderBottom:'3px solid #5f3f3f'});
        $(".input-search").attr('placeholder','请输入开发区名,例如:北京经济技术开发区');
    });


    $(".search").on('click',function () {
        var keyWord = $('#searchBtn').val().replace(/^\s+|\s+$/gm,'');
        if(keyWord != ''){
            if($("#username").text() ===''){
                $.ajax({
                    type:'GET',
                    url:'/reqArea',
                    data:{'areaName':keyWord},
                    dataType:'json',
                    async:false,
                    success:function (rstList) {
                        if(rstList.length >1 ){
                            $("#search_form").attr('action','/search');
                            $("#search_form").submit();
                        }
                        if(rstList.length === 1 ){
                            var txt = '请先登录';
                            window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
                        }
                        if (rstList.length === 0){
                            $("#search_form").attr('action','/search');
                            $("#search_form").submit();
                        }
                    }
                })
            }
            else {
                $("#search_form").attr('action','/search');
                $("#search_form").submit();
            }
        }
    });

    $("#searchBtn").bind('keypress',function(event){


        if(event.keyCode == 13)

        {
            $('.search').click();
        }

    });

    function checkEmail() {
        var username = $("#emailUsername").val();
        if(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(username)){
            clearRegistTip();
        }
        else{
            $("#registEmailTips").css('color','red').html('请填写正确的邮箱') ;
        }

    }

    function checkEmailPassword() {
        var password = $("#emailPassword").val();
        if(password === '' ){
            $("#registEmailTips").css('color','red').html('密码不能为空');
        }
        else if(!/^[a-zA-z0-9]{6,15}$/.test(password)){
            $("#registEmailTips").css('color','red').html('密码长度6-15个字符');
        }
        else{
            clearRegistTip();
            checkEmail();
        }

    }
    function chenckEmailVrcode() {
        var param = '';
        var code = $("#emailVercode").val();
        $.ajax({
            type:'get',
            dataType: "text",
            async:false,
            url : '/getCodeSession',
            data : {'date':new Date().getTime()},
            success : function(rst){
                if(rst != '') {
                    param = rst;
                }
            }
        });
        if(code === ''){
            $("#registEmailTips").css('color','red').html('请输入验证码');
        }
        else if (code.toUpperCase() != param){
            $("#registEmailTips").css('color','red').html('验证码错误');
        }else {
            clearRegistTip();
            checkEmailPassword();
        }

    }

    function clearRegistTip() {
        var tip = $("#registEmailTips").html();
        if (tip != '') $("#registEmailTips").html('');
    }

    $("#emailUsername").change(checkEmail);
    $("#emailPassword").on('change',checkEmailPassword);
    $("#emailVercode").on('change',chenckEmailVrcode);

    $("#registBtn").on('click',function () {
        chenckEmailVrcode();
        var tip = $("#registEmailTips").html();
        if (tip === ''){
            $.ajax({
                type:'post',
                dataType: "text",
                url : '/registConfirm',
                data : {'loginname':$('#emailUsername').val(),'password':$('#emailPassword').val()
                        },
                success : function(rst){
                    if(rst === 'input'){
                        $("#registEmailTips").css('color','red').html('该用户已经被注册');
                    }
                    if(rst === 'success') {
                        $('#registBox').css('display','none');
                        $("#confirmEmail").fadeIn("slow");
                    }
                    if(rst === 'error') {
                        $("#registEmailTips").css('color','red').html('发送失败，请重试');
                    }
                }
            })
        }
    });

    $("#IKnow").on('click',function () {
        $("#closeBtn5").click();
    });

    $("#closeBtn5").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
        $("#confirmEmail").fadeOut("fast");
        $("#mask").css({ display: 'none' });
    });
    
    $('#changeVercode').on('click',function() {
        document.getElementById('imagecode').src='/getVercode?d='+new Date().getTime();
    });

} );