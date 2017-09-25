package cn.peopleinvest.controller;

import cn.peopleinvest.mapper.WxCardcaseDaoImpl;
import cn.peopleinvest.model.WxCard;
import cn.peopleinvest.model.WxCardId;
import cn.peopleinvest.model.WxCardcase;
import cn.peopleinvest.model.WxUser;
import cn.peopleinvest.util.ObjectMapperWoker;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cardcase")
public class WxCardcaseController {

    @Resource
    private WxCardcaseDaoImpl wxCardcaseDao;

    @GetMapping(value = "/cardData/{id}")
    public String getOtherCard(@RequestHeader("token") String header,@PathVariable String id) throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("cardData",wxCardcaseDao.findObjectByOne(id));
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }

    @PostMapping(value = "/cardData")
    public String addOtherCard(@RequestHeader("token") String header, @RequestBody WxCard data) throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("result","success");
        wxCardcaseDao.saveCard(new WxCardcase(header.concat(data.getId())));
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }
}
