package cn.peopleinvest.controller;

import cn.peopleinvest.mapper.WxItemDaoImpl;
import cn.peopleinvest.mapper.WxOrderDaoImpl;
import cn.peopleinvest.model.WxItem;
import cn.peopleinvest.model.WxOrder;
import cn.peopleinvest.util.ObjectMapperWoker;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/shop")
public class WxShopContorller {


    private static Logger logger = Logger.getLogger(WxShopContorller.class);

    @Resource
    private WxItemDaoImpl wxItemDao;
    @Resource
    private WxOrderDaoImpl wxOrderDao;

    @GetMapping(value = "/itemList")
    public String getItemList(@RequestHeader(value = "token") String Header) throws IOException {

        List<WxItem> itemList = wxItemDao.getItemList();

        return ObjectMapperWoker.getInstance().writeValueAsString(itemList);
    }

    @PostMapping(value = "/deal")
    public String addOrder(@RequestHeader(value = "token") String header,@RequestBody WxOrder order) throws IOException {
        Map map = new HashMap();
        map.put("result","success");
        order.setOrderId(UUID.randomUUID().toString());
        order.setUserId(header);
        try{
            wxOrderDao.saveOrder(order);
        }
        catch (Exception e){
            map.put("result","fail");
            return ObjectMapperWoker.getInstance().writeValueAsString(map);
        }
        return ObjectMapperWoker.getInstance().writeValueAsString(map);
    }

    @GetMapping(value = "/orderList")
    public String getOrderList(@RequestHeader(value = "token") String header) throws IOException {
        Map map = new HashMap();
        map.put("orderList",wxOrderDao.getOrderByUserId(header));
        return ObjectMapperWoker.getInstance().writeValueAsString(map);
    }

}
