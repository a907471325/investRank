package cn.peopleinvest.mapper;

import cn.peopleinvest.model.WxOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxOrderDaoImpl {


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获取订单总数
     * @return
     */
    public int getTotalSum(){
       return (int) mongoTemplate.count(new Query(), WxOrder.class);
    }

    /**
     * 保存订单
     * @param wxOrder
     */
    public void saveOrder(WxOrder wxOrder){
        mongoTemplate.save(wxOrder);
    }

    /**
     * 获取用户的所有订单
     * @param userId
     * @return
     */
    public List<WxOrder> getOrderByUserId(String userId){
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query,WxOrder.class);
    }



}
