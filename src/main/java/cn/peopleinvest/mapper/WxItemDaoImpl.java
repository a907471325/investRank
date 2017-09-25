package cn.peopleinvest.mapper;

import cn.peopleinvest.model.WxItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxItemDaoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<WxItem> getItemList(){
       return mongoTemplate.findAll(WxItem.class);
    }
}
