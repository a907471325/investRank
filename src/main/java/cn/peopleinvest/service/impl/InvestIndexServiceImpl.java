package cn.peopleinvest.service.impl;

import cn.peopleinvest.mapper.InvestindexMapper;
import cn.peopleinvest.mapper.LoginuserMapper;
import cn.peopleinvest.model.Area;
import cn.peopleinvest.model.Investindex;
import cn.peopleinvest.model.Loginuser;
import cn.peopleinvest.model.User;
import cn.peopleinvest.service.InvestIndexService;
import cn.peopleinvest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class InvestIndexServiceImpl implements InvestIndexService {

    @Autowired
    private InvestindexMapper investindexMapper;

    @Override
    public List<Investindex> getIndexs(Investindex investindex) {
        return investindexMapper.selectByAreaName(investindex);
    }

    @Override
    public List<Investindex> getResultList(Investindex investindex) {
        return investindexMapper.getListByAreaName(investindex);
    }
}
