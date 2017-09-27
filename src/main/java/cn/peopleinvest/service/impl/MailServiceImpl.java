package cn.peopleinvest.service.impl;

import cn.peopleinvest.config.ConstVal;
import cn.peopleinvest.service.MailService;
import cn.peopleinvest.util.Md5Encrypt;
import cn.peopleinvest.mapper.LoginuserMapper;
import cn.peopleinvest.mapper.UserMapper;
import cn.peopleinvest.model.Loginuser;
import cn.peopleinvest.model.User;
import cn.peopleinvest.util.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginuserMapper loginuserMapper;


    @Transactional
    @Override
    public void processregister(Loginuser loginuser) throws Throwable {

        if(loginuser.getUserId() == null){
            User user = new User();
            String id = UUID.randomUUID().toString();
            user.setId(id);
            user.setIsPush(ConstVal.IS_PUSH);
            user.setJoinTime(new Date());
            user.setRole("normal");

            loginuser.setState(ConstVal.USER_STATUS_INVALID);
            loginuser.setType("email");
            loginuser.setUserId(id);
            loginuser.setRemark(Md5Encrypt.EncoderByMd5(loginuser.getLoginname()));

            userMapper.insert(user);//保存注册信息
            loginuserMapper.insert(loginuser);
        }
        else if (loginuser.getRemark() == null){
            loginuser.setRemark(Md5Encrypt.EncoderByMd5(loginuser.getLoginname()));
            loginuser.setType("email");
            loginuser.setState(ConstVal.USER_STATUS_INVALID);
            loginuserMapper.insert(loginuser);
        }

        String url = "http://" + ConstVal.host +
                ":8080/regist?email=" +
                loginuser.getLoginname() +
                "&token=" +
                loginuser.getRemark();

        SendMail.send_template(loginuser.getLoginname(), url);

    }

    @Async
    @Transactional
    @Override
    public void processUpdate(Loginuser loginuser) throws Throwable {
        loginuser.setRemark(Md5Encrypt.EncoderByMd5(loginuser.getLoginname()));
        loginuser.setType("email");
        loginuser.setState(ConstVal.USER_STATUS_INVALID);
        loginuserMapper.updateByPrimaryKeySelective(loginuser);

        String url = "http://" + ConstVal.host +
                ":8080/regist?email=" +
                loginuser.getLoginname() +
                "&token=" +
                loginuser.getRemark();

        SendMail.send_template(loginuser.getLoginname(), url);
    }

}
