package cn.peopleinvest.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cn.peopleinvest.config.Constant;
import cn.peopleinvest.model.PayInfo;
import cn.peopleinvest.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序支付控制器
 */
@Controller
public class PayController {

    private static Logger log = Logger.getLogger(PayController.class);

    /**
     * 预支付,小程序发起支付前调用
     * @param code 小程序通过微信登陆后返回的参数
     * @param name 商品名
     * @param price 商品价格
     * @param request request
     * @return prepareId,randomNonceStr
     */
    @ResponseBody
    @GetMapping(value = "/prepay", produces = "text/html;charset=UTF-8")
    public String prePay(@RequestParam String code, @RequestParam String name, @RequestParam int price, HttpServletRequest request) {

        String content = null;
        Map<String,String> map = new HashMap<String,String>();
        ObjectMapper mapper = new ObjectMapper();

        boolean result = true;
        String info = "";

        log.error("\n======================================================");
        log.error("code: " + code);

        String openId = getOpenId(code);
        if(StringUtils.isBlank(openId)) {
            result = false;
            info = "获取到openId为空";
        } else {
            openId = openId.replace("\"", "").trim();

            String clientIP = CommonUtil.getClientIp(request);

            log.error("openId: " + openId + ", clientIP: " + clientIP);

            String randomNonceStr = RandomUtils.generateMixString(32);
            String prepayId = unifiedOrder(openId, clientIP, randomNonceStr,price,name);

            log.error("prepayId: " + prepayId);

            if(StringUtils.isBlank(prepayId)) {
                result = false;
                info = "出错了，未获取到prepayId";
            } else {
                map.put("prepayId", prepayId);
                map.put("nonceStr", randomNonceStr);
            }
        }

        try {
            map.put("result", result?"true":"false");
            map.put("info", info);
            content = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }


    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APP_ID +
                "&secret=" + Constant.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";

        HttpUtil httpUtil = new HttpUtil();
        try {

            HttpResult httpResult = httpUtil.doGet(url, null, null);

            if(httpResult.getStatusCode() == 200) {

                JsonParser jsonParser = new JsonParser();
                JsonObject obj = (JsonObject) jsonParser.parse(httpResult.getBody());

                log.error("getOpenId: " + obj.toString());

                if(obj.get("errcode") != null) {
                    log.error("getOpenId returns errcode: " + obj.get("errcode"));
                    return "";
                } else {
                    return obj.get("openid").toString();
                }
                //return httpResult.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 调用统一下单接口
     * @param openId
     */
    private String unifiedOrder(String openId, String clientIP, String randomNonceStr,int price,String name) {

        try {

            String url = Constant.URL_UNIFIED_ORDER;

            PayInfo payInfo = createPayInfo(openId, clientIP, randomNonceStr,price,name);
            String md5 = getSign(payInfo);
            payInfo.setSign(md5);

            log.error("md5 value: " + md5);

            String xml = CommonUtil.payInfoToXML(payInfo);
            xml = xml.replace("__", "_").replace("<![CDATA[1]]>", "1");
            //xml = xml.replace("__", "_").replace("<![CDATA[", "").replace("]]>", "");
            log.error(xml);

            StringBuffer buffer = HttpUtil.httpsRequest(url, "POST", xml);
            log.error("unifiedOrder request return body: \n" + buffer.toString());
            Map<String, String> result = CommonUtil.parseXml(buffer.toString());


            String return_code = result.get("return_code");
            if(StringUtils.isNotBlank(return_code) && return_code.equals("SUCCESS")) {

                String return_msg = result.get("return_msg");
                if(StringUtils.isNotBlank(return_msg) && !return_msg.equals("OK")) {
                    //log.error("统一下单错误！");
                    return "";
                }

                String prepay_Id = result.get("prepay_id");
                return prepay_Id;

            } else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private PayInfo createPayInfo(String openId, String clientIP, String randomNonceStr,int price,String name) {

        Date date = new Date();
        String timeStart = TimeUtils.getFormatTime(date, Constant.TIME_FORMAT);
        String timeExpire = TimeUtils.getFormatTime(TimeUtils.addDay(date, Constant.TIME_EXPIRE), Constant.TIME_FORMAT);

        String randomOrderId = CommonUtil.getRandomOrderId();

        PayInfo payInfo = new PayInfo();
        payInfo.setAppid(Constant.APP_ID);
        payInfo.setMch_id(Constant.MCH_ID);
        payInfo.setNonce_str(randomNonceStr);
        payInfo.setBody(name);
        payInfo.setOut_trade_no(randomOrderId);
        payInfo.setTotal_fee(price);
        payInfo.setSpbill_create_ip(clientIP);
        payInfo.setTime_start(timeStart);
        payInfo.setTime_expire(timeExpire);
        payInfo.setNotify_url(Constant.URL_NOTIFY);
        payInfo.setTrade_type("JSAPI");
        payInfo.setLimit_pay("no_credit");
        payInfo.setOpenid(openId);

        return payInfo;
    }

    private String getSign(PayInfo payInfo) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=" + payInfo.getAppid())
                .append("&body=" + payInfo.getBody())
                .append("&limit_pay=" + payInfo.getLimit_pay())
                .append("&mch_id=" + payInfo.getMch_id())
                .append("&nonce_str=" + payInfo.getNonce_str())
                .append("&notify_url=" + payInfo.getNotify_url())
                .append("&openid=" + payInfo.getOpenid())
                .append("&out_trade_no=" + payInfo.getOut_trade_no())
                .append("&spbill_create_ip=" + payInfo.getSpbill_create_ip())
                .append("&time_expire=" + payInfo.getTime_expire())
                .append("&time_start=" + payInfo.getTime_start())
                .append("&total_fee=" + payInfo.getTotal_fee())
                .append("&trade_type=" + payInfo.getTrade_type())
                .append("&key=" + Constant.APP_KEY);

        log.error("排序后的拼接参数：" + sb.toString());

        return CommonUtil.getMD5(sb.toString().trim()).toUpperCase();
    }



}


