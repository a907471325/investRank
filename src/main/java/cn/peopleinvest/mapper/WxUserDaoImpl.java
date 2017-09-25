package cn.peopleinvest.mapper;

import cn.peopleinvest.model.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;



@Repository
public class WxUserDaoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param object
     */
    public void saveUser(WxUser object) {
        mongoTemplate.save(object);
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    public WxUser findObjectByOne(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        WxUser result =  mongoTemplate.findOne(query , WxUser.class);

        return result;
    }

    /**
     * 更新对象
     * @param user
     */
    public void updateUser(WxUser user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getNickName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,WxUser.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,WxUser.class);
    }

    /**
     * 删除对象
     * @param id
     */
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,WxUser.class);
    }
}
