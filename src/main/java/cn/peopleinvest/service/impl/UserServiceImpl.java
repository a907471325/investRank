package cn.peopleinvest.service.impl;

import cn.peopleinvest.mapper.LoginuserMapper;
import cn.peopleinvest.mapper.UserMapper;
import cn.peopleinvest.model.Loginuser;
import cn.peopleinvest.model.LoginuserKey;
import cn.peopleinvest.model.User;
import cn.peopleinvest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginuserMapper loginuserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void insertLoginUser(Loginuser loginuser) {
        loginuserMapper.insert(loginuser);
    }

    @Override
    public Loginuser queryByPrimaryKey(LoginuserKey key) {
        return loginuserMapper.selectByPrimaryKey(key);
    }

    @Override
    public Loginuser loginUser(Loginuser loginuser) {
        if (StringUtils.isEmpty(loginuser.getLoginname())||StringUtils.isEmpty(loginuser.getPassword())){
            throw new RuntimeException("用户名和密码不能为空");
        }
        Loginuser loginuserdb = loginuserMapper.selectByExample(loginuser);
        return loginuserdb;
    }

    @Override
    public Loginuser querySelective(Loginuser loginuser) {
        return loginuserMapper.selectSelective(loginuser);
    }

    @Override
    public User queryById(String id) {
        return null;
    }

    @Override
    @Transactional
    public void updateLoginUser(Loginuser user) {
        loginuserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Loginuser queryByUnionId(LoginuserKey key) {
        return loginuserMapper.selectByPrimaryKey(key);
    }
}
