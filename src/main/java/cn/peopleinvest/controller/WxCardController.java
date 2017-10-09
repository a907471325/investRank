package cn.peopleinvest.controller;


import cn.peopleinvest.mapper.WxCardDaoImpl;
import cn.peopleinvest.model.WxCard;
import cn.peopleinvest.util.ObjectMapperWoker;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * 微信小程序名片控制器
 */
@RestController
@RequestMapping(value = "/card")
public class WxCardController {

    @Autowired
    private WxCardDaoImpl wxCardDao;

    /**
     * 根据用户ID获取名片
     * @param token
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/id")
    public String getCard(@RequestHeader("token") String token) throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("cardData",wxCardDao.findObjectByOne(token));
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }

    /**
     * 根据卡片ID获取名片
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/cardId")
    public String getCardFromId(@RequestHeader("token") String id) throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("cardData",wxCardDao.findObjectById(id));
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }

    /**
     * 添加新名片
     * @param token
     * @param data
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/card")
    public String addCard(@RequestHeader("token") String token, @RequestBody WxCard data) throws IOException {
        JSONObject jo = new JSONObject();
        data.setOwnerID(token);
        wxCardDao.saveCard(data);
        jo.put("result","success");
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }

    /**
     * 删除名片
     * @param token
     * @param id
     * @return
     * @throws IOException
     */
    @DeleteMapping(value = "/card/{id}")
    public String delCard(@RequestHeader("token") String token,@PathVariable String id) throws IOException {
        JSONObject jo = new JSONObject();
        if(StringUtils.isNotBlank(id)) {
            wxCardDao.deleteCardById(id);
            jo.put("result","success");
        }
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }


}
