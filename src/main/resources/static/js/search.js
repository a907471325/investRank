var checkBeforeEnter=function(e,s,o){""===$("#username").text()?window.wxc.xcConfirm("请先登录",window.wxc.xcConfirm.typeEnum.info):window.location.href="/detail?areaName="+e+"&investIndex="+s+"&localRank="+o};$(function(){function e(){var e=$("#emailUsername").val();/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(e)?n():$("#registEmailTips").css("color","red").html("请填写正确的邮箱")}function s(){var s=$("#emailPassword").val();""===s?$("#registEmailTips").css("color","red").html("密码不能为空"):/^[a-zA-z0-9]{6,15}$/.test(s)?(n(),e()):$("#registEmailTips").css("color","red").html("密码长度6-15个字符")}function o(){var e="",o=$("#emailVercode").val();$.ajax({type:"get",dataType:"text",async:!1,url:"/getCodeSession",data:{date:(new Date).getTime()},success:function(s){""!=s&&(e=s)}}),""===o?$("#registEmailTips").css("color","red").html("请输入验证码"):o.toUpperCase()!=e?$("#registEmailTips").css("color","red").html("验证码错误"):(n(),s())}function n(){""!=$("#registEmailTips").html()&&$("#registEmailTips").html("")}function c(){var e=$("#phoneUsername").val();/^1[34578]\d{9}$/.test(e)?l():$("#registPhoneTips").css("color","red").html("手机号有误")}function i(){var e=$("#phonePassword").val();""===e?$("#registPhoneTips").css("color","red").html("密码不能为空"):/^[a-zA-z0-9]{6,15}$/.test(e)?(l(),c()):$("#registPhoneTips").css("color","red").html("密码格式位6-15位数字或字母")}function t(){""===$("#phoneVercode").val()?$("#registPhoneTips").css("color","red").html("请输入验证码"):(l(),i())}function a(){var e=$("#getVercode");if(0==d)return e.attr("disabled",!1),e.val("点击获取"),void(d=60);e.attr("disabled",!0),e.val("刷新("+d+")"),d--,setTimeout(function(){a()},1e3)}var r=$(document.body).width();$("body").css("width",r),$(".login").on("click",function(){$("body").append("<div id='mask'></div>"),$("#mask").addClass("mask").fadeIn("slow"),$("#loginAsWeixin").fadeIn("slow")}),$(".regist").on("click",function(){$("body").append("<div id='mask'></div>"),$("#mask").addClass("mask").fadeIn("slow"),$("#registAsWeixin").fadeIn("slow")}),$("#login").on("click",function(){checkPassword(),""===$("#loginTips").html()&&$.ajax({type:"post",dataType:"json",url:"/loginCheck",async:!1,data:{loginname:$("#loginName").val(),password:$("#password").val()},success:function(e){null===e.loginname?$("#loginTips").css("color","red").html("用户名或密码错误"):loginSearch()}})}),$("#jump-regist").on("click",function(){$("#registAsWeixin").css("display","none"),$("#registBox").css("display","inline")}),$("#jump-login").on("click",function(){$("#loginAsWeixin").css("display","none"),$("#loginBox").css("display","inline")}),$("#exitIcon").on("click",function(){$("#logout").attr("action","/logoutSearch"),$("#logout").submit()}),$("#closeBtn").hover(function(){$(this).css({color:"black"})},function(){$(this).css({color:"#999"})}).on("click",function(){$("#loginBox").fadeOut("fast"),$("#mask").css({display:"none"})}),$("#closeBtn2").hover(function(){$(this).css({color:"black"})},function(){$(this).css({color:"#999"})}).on("click",function(){$("#registBox").fadeOut("fast"),$("#mask").css({display:"none"})}),$("#closeBtn3").hover(function(){$(this).css({color:"black"})},function(){$(this).css({color:"#999"})}).on("click",function(){$("#registAsWeixin").fadeOut("fast"),$("#mask").css({display:"none"})}),$("#closeBtn4").hover(function(){$(this).css({color:"black"})},function(){$(this).css({color:"#999"})}).on("click",function(){$("#loginAsWeixin").fadeOut("fast"),$("#mask").css({display:"none"})}),$("#choosephone").on("click",function(){$(".regist-email-div").css({display:"none"}),$("#chooseemail").css({textDecoration:"none"}),$(this).css({textDecoration:"underline"}),$(".regist-phone-div").css({display:"inline"})}),$("#chooseemail").on("click",function(){$(".regist-phone-div").css({display:"none"}),$("#choosephone").css({textDecoration:"none"}),$(this).css({textDecoration:"underline"}),$(".regist-email-div").css({display:"inline"})}),$("#select_area").on("click",function(){$("#select_dev_area").css({borderBottom:"none"}),$("#select_area").css({borderBottom:"2px solid #FFFFFF"}),$(".input-search").attr("placeholder","请输入地域名,例如:北京,信阳,哈尔滨")}),$("#select_dev_area").on("click",function(){$("#select_area").css({borderBottom:"none"}),$("#select_dev_area").css({borderBottom:"2px solid #FFFFFF"}),$(".input-search").attr("placeholder","请输入开发区名,例如:北京经济技术开发区")}),$(".indexColumn").hover(function(){$(this).css("backgroundColor","#f5f5f5")},function(){$(this).css("backgroundColor","#FFFFFF")}),$(".search").on("click",function(){var e=$("#searchBtn").val().replace(/^\s+|\s+$/gm,"");""!=e&&(""===$("#username").text()?$.ajax({type:"GET",url:"/reqArea",data:{areaName:e},dataType:"json",async:!1,success:function(e){e.length>1&&($("#search_form").attr("action","/search"),$("#search_form").submit()),1===e.length&&window.wxc.xcConfirm("请先登录",window.wxc.xcConfirm.typeEnum.info),0===e.length&&($("#search_form").attr("action","/search"),$("#search_form").submit())}}):($("#search_form").attr("action","/search"),$("#search_form").submit()))}),$("#searchBtn").bind("keypress",function(e){13==e.keyCode&&$(".search").click()}),$("#emailUsername").change(e),$("#emailPassword").on("change",s),$("#emailVercode").on("change",o),$("#registBtn").on("click",function(){o(),""===$("#registEmailTips").html()&&$.ajax({type:"post",dataType:"text",url:"/registConfirm",data:{loginname:$("#emailUsername").val(),password:$("#emailPassword").val()},success:function(e){"input"===e&&$("#registEmailTips").css("color","red").html("该用户已经被注册"),"success"===e&&($("#registBox").css("display","none"),$("#confirmEmail").fadeIn("slow")),"error"===e&&$("#registEmailTips").css("color","red").html("发送失败，请重试")}})}),$("#IKnow").on("click",function(){$("#closeBtn5").click()}),$("#closeBtn5").hover(function(){$(this).css({color:"black"})},function(){$(this).css({color:"#999"})}).on("click",function(){$("#confirmEmail").fadeOut("fast"),$("#mask").css({display:"none"})}),$("#changeVercode").on("click",function(){document.getElementById("imagecode").src="/getVercode?d="+(new Date).getTime()});var l=function(){""!=$("#registPhoneTips").html()&&$("#registPhoneTips").html("")};$("#phoneUsername").change(c),$("#phonePassword").on("change",i),$("#phoneVercode").on("change",t),$("#getVercode").on("click",function(){c(),""==$("#registPhoneTips").html()&&($.ajax({cache:!1,method:"get",dataType:"text",url:"sendMsg",data:{phone:$("#phoneUsername").val()}}),a())}),$("#phoneRegistSubmit").on("click",function(){t(),""==$("#registPhoneTips").html()&&$.ajax({async:!1,method:"GET",dataType:"text",url:"checkCode",data:{code:$("#phoneVercode").val()},success:function(e){"success"==e?$.ajax({async:!1,dataType:"text",url:"checkPhone",data:{phone:$("#phoneUsername").val()},success:function(e){"success"==e?$("#regist_phone").attr("method","post").attr("action","/registPhone").submit():$("#registPhoneTips").css("color","red").html("手机号已被注册")}}):$("#registPhoneTips").css("color","red").html("验证码错误")}})});var d=60});