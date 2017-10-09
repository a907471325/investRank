package cn.peopleinvest.controller;

import cn.peopleinvest.mapper.WxUserDaoImpl;
import cn.peopleinvest.model.WxUser;
import cn.peopleinvest.util.CommonUtil;
import cn.peopleinvest.util.ObjectMapperWoker;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 小程序登录控制器
 */
@RestController
public class WxUserController {

    @Resource
    private WxUserDaoImpl wxUserDao;

    private static Logger logger = Logger.getLogger(WxUserController.class);

    @PostMapping(value = "/wxlogin")
    @ResponseBody
    public String wxLogin(@RequestBody Map<String,String> data, HttpSession session) throws IOException {

        Map map = new HashMap<String,String>();
        //获取OPENID做为用户唯一标识
        JSONObject obj = CommonUtil.decryptUserInfo(data.get("encryptedData"),data.get("session_key"),data.get("iv"),logger);
        String token = obj.getString("openId");
        //用户ID做为令牌
        map.put("token",token);
        wxUserDao.saveUser(new WxUser(token,obj.getString("nickName")));
        return ObjectMapperWoker.getInstance().writeValueAsString(map);
    }


//
//    public  static void main(String [] args){
//        String encryptedData = "A/5Xi5btKGrxurt8LxlmcDmWTUHoFNsD5IRpqkwGYGWl1sPxKt9pCW2/Dkd4C7XceLWS/2TlxV5doHcEMgbHCHDd3WgtGMGTv8mieC9PjHoTjiIe2RVlqupXkCSQ5122GYv5SXTpoSKfU4DDxwf+pXagAkiVsF2CvLp5h4p1nB94rwVuNbKvaFWB37r1cbnktt7PchU5CRP4XzGgtdF9wpGC80zRza0Zpw4Lv10P5aWPeXRdacV4+RVoi5eB5XqxisNVSoG6E1JnUNsE2A2HJkEgoR+oOLz22vG7RomdpbkEZDtvGr4jYy3WGmZz31aZ1BCN0Xs0mTk5sjsvAbnHCNg/359YYp9fehANtW0OirkecgMQ10sy7qToyAJGdAXl/JBZbcHo4UXC/TKiyy1u+Io1deVY14pjzpG807lsvcGTWE5s5IxOQV+av3dLoE8m5ZSpsif3oSwq9fyLIq9Dvg==";
//        String session_key = "gNzkY3WMU1fJr7VmO9zy1Q==";
//        String iv = "6yZfXhi0sWLnXod+8hODZg==";
//    }



}
