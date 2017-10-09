package cn.peopleinvest.controller;

import cn.peopleinvest.mapper.WxCardDaoImpl;
import cn.peopleinvest.mapper.WxCardcaseDaoImpl;
import cn.peopleinvest.model.WxCard;
import cn.peopleinvest.model.WxCardId;
import cn.peopleinvest.model.WxCardcase;
import cn.peopleinvest.model.WxUser;
import cn.peopleinvest.util.ObjectMapperWoker;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * 小程序名片夹操作控制器
 */
@RestController
@RequestMapping(value = "/cardcase")
public class WxCardcaseController {

    @Resource
    private WxCardcaseDaoImpl wxCardcaseDao;

    @Resource
    private WxCardDaoImpl wxCardDao;


    /**
     * 检查指定id的卡片是否存在
     * @param header 保存用户唯一微信Id的请求头
     * @param id 要获取的卡片id
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/cardData/{id}")
    public String getOtherCard(@RequestHeader("token") String header,@PathVariable String id) throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("cardData",wxCardcaseDao.findObjectByOne(id));
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }

    /**
     * 将名片保存至名片夹
     * @param header
     * @param data
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/cardData")
    public String addOtherCard(@RequestHeader("token") String header, @RequestBody WxCard data) throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("result","success");
        wxCardcaseDao.saveCard(new WxCardcase(header.concat(data.getId())));
        return ObjectMapperWoker.getInstance().writeValueAsString(jo);
    }

    /**
     * 获取名片夹里的所有名片
     * @param header
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/case")
    public String getCardCaseData(@RequestHeader("token") String header) throws IOException {

       List<WxCardcase> cardList =  wxCardcaseDao.findObjectByUserId(header);
       List<WxCard> result = new LinkedList<WxCard>();
       if(!cardList.isEmpty()){
           for (WxCardcase card : cardList){
              String  CardId = card.getId().substring(header.length());
              WxCard aCard = wxCardDao.findObjectById(CardId);
              result.add(aCard);
           }
       }
       return ObjectMapperWoker.getInstance().writeValueAsString(result);
    }

    /**
     * 获取名片夹里某个名片的信息
     * @param header
     * @param id
     * @return
     * @throws IOException
     */
    @DeleteMapping(value = "/cardData/{id}")
    public String getCardCaseData(@RequestHeader("token") String header,@PathVariable String id) throws IOException {

        try{
            wxCardcaseDao.deleteCardById(header+id);
        }
        catch (Exception e){
            return "failed";
        }
        return "success";
    }
}
