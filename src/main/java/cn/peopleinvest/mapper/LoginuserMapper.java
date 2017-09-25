package cn.peopleinvest.mapper;

import cn.peopleinvest.model.Loginuser;
import cn.peopleinvest.model.LoginuserKey;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

@Component
public interface LoginuserMapper {
    int deleteByPrimaryKey(LoginuserKey key);

    int insert(Loginuser record);

    int insertSelective(Loginuser record);

    Loginuser selectByPrimaryKey(LoginuserKey key);

    Loginuser selectSelective(Loginuser loginuser);

    Loginuser selectByExample(Loginuser loginuser);

    int updateByPrimaryKeySelective(Loginuser record);

    int updateByPrimaryKey(Loginuser record);
}