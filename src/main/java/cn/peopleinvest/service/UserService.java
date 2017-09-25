package cn.peopleinvest.service;

import cn.peopleinvest.mapper.UserMapper;
import cn.peopleinvest.model.Loginuser;
import cn.peopleinvest.model.LoginuserKey;
import cn.peopleinvest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 90747 on 2017/8/7.
 */
public interface UserService {



    void insertUser(User user);

    void insertLoginUser(Loginuser user);

    Loginuser loginUser(Loginuser loginuser);

    Loginuser querySelective(Loginuser loginuser);

    User queryById(String id);

    Loginuser queryByPrimaryKey(LoginuserKey key);

    void updateLoginUser(Loginuser user);

    Loginuser queryByUnionId(LoginuserKey key);



}
