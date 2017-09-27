$( function() {
    //var browser_width = $(document.body).width();
    //$("body").css("width",browser_width);
    $("#exitIcon").on('click', function () {
        /*注销*/
        window.location.href = 'logout';

    });

    $("#select_area").on('click', function () {
        $("#select_dev_area").css({borderBottom: 'none'});
        $("#select_area").css({borderBottom: '3px solid #FFFFFF'});
        $(".input-search").attr('placeholder', '请输入地域名,例如:北京,信阳,哈尔滨');
    });

    $("#select_dev_area").on('click', function () {
        $("#select_area").css({borderBottom: 'none'});
        $("#select_dev_area").css({borderBottom: '3px solid #FFFFFF'});
        $(".input-search").attr('placeholder', '请输入开发区名,例如:北京经济技术开发区');
    });

    $(".search").on('click',function () {
        var keyWord = $('#searchBtn').val().replace(/^\s+|\s+$/gm, '');
        if (keyWord != '') {
            if ($("#username").text() === '') {
                window.location.href="index";
            }
            else {
                $("#search_form").attr('action', '/search');
                $("#search_form").submit();
            }
        }
    });
});


$(function getData(){
    var data = $("#keyWord").val();
    $.ajax({
            type:'get',
            dataType: "json",
            url : '/reqData',
            async : false,
            data : {'areaName':data},
        success : function(rstList){
            if(rstList === null) alert("服务器异常");
            else {
                $('#container').highcharts({
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: '月平均指数'
                    },
                    xAxis: {
                        categories: [rstList[11].recordDate, rstList[10].recordDate, rstList[9].recordDate,
                            rstList[8].recordDate,rstList[7].recordDate,rstList[6].recordDate,rstList[5].recordDate,
                            rstList[4].recordDate,rstList[3].recordDate, rstList[2].recordDate,
                            rstList[1].recordDate, rstList[0].recordDate]
                    },
                    yAxis: {
                        title: {
                            text: '投 资 指 数'
                        }
                    },
                    plotOptions: {
                        line: {
                            dataLabels: {
                                enabled: true          // 开启数据标签
                            },
                            enableMouseTracking: true // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                        }
                    },
                    series: [{
                        name: rstList[0].areaName,
                        data: [rstList[11].investIndex, rstList[10].investIndex, rstList[9].investIndex, rstList[8].investIndex,
                            rstList[7].investIndex, rstList[6].investIndex,rstList[5].investIndex,
                            rstList[4].investIndex,rstList[3].investIndex,rstList[2].investIndex, rstList[1].investIndex, rstList[0].investIndex]
                    }]
                });
            }
        }
    });
});

