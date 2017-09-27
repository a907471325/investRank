package cn.peopleinvest.mapper;

import cn.peopleinvest.model.WxCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class WxCardDaoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param object
     */
    public void saveCard(WxCard object) {
        mongoTemplate.save(object);
    }

    /**
     * 根据用户名查询对象
     * @param ownerID
     * @return
     */
    public WxCard findObjectByOne(String ownerID) {
        Query query=new Query(Criteria.where("ownerID").is(ownerID));
        WxCard result =  mongoTemplate.findOne(query , WxCard.class);
        return result;
    }

    public WxCard findObjectById(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        WxCard result =  mongoTemplate.findOne(query , WxCard.class);
        return result;
    }

    /**
     * 更新对象
     * @param user
     */
    public void updateCard(WxCard user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update()
                .set("name",user.getName())
                .set("address",user.getAddress())
                .set("avatarUrl",user.getAvatarUrl())
                .set("companyName",user.getCompanyName())
                .set("email",user.getEmail())
                .set("intro",user.getIntro())
                .set("loglat",user.getLoglat())
                .set("mobile",user.getMobile())
                .set("need",user.getNeed())
                .set("project",user.getProject())
                .set("title",user.getTitle());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,WxCard.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,WxCard.class);
    }

    /**
     * 删除对象
     * @param id
     */
    public void deleteCardById(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,WxCard.class);
    }

}
