package cn.peopleinvest.mapper;

import cn.peopleinvest.model.WxCardcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class WxCardcaseDaoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param object
     */
    public void saveCard(WxCardcase object) {
        mongoTemplate.save(object);
    }

    /**
     * 根据用户名查询对象
     * @param mixId
     * @return
     */
    public WxCardcase findObjectByOne(String mixId) {
        Query query=new Query(Criteria.where("id").is(mixId));
        WxCardcase result =  mongoTemplate.findOne(query , WxCardcase.class);
        return result;
    }

    /**
     * 删除对象
     * @param id
     */
    public void deleteCardById(String id,String id2) {
        Query query=new Query(Criteria.where("id").is(id).and("wxCard").is(id2));
        mongoTemplate.remove(query,WxCardcase.class);
    }
}
