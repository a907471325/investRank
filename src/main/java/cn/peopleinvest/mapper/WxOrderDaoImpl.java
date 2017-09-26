package cn.peopleinvest.mapper;

import cn.peopleinvest.model.WxOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class WxOrderDaoImpl {


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * @return
     */
    public int getTotalSum(){

       return (int) mongoTemplate.count(new Query(), WxOrder.class);
    }

    public void saveOrder(WxOrder wxOrder){

        mongoTemplate.save(wxOrder);
    }


}
