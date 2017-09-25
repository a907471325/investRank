package cn.peopleinvest.controller;

import cn.peopleinvest.mapper.WxItemDaoImpl;
import cn.peopleinvest.model.WxItem;
import cn.peopleinvest.util.ObjectMapperWoker;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/shop")
public class WxShopContorller {

    private  static Logger logger = Logger.getLogger(WxShopContorller.class);

    @Resource
    private WxItemDaoImpl wxItemDao;

    @GetMapping(value = "/itemList")
    public String getItemList(@RequestHeader(value = "token") String Header) throws IOException {

        List<WxItem> itemList = wxItemDao.getItemList();

        return ObjectMapperWoker.getInstance().writeValueAsString(itemList);
    }

}
