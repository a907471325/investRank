package cn.peopleinvest.mapper;

import cn.peopleinvest.model.Area;
import cn.peopleinvest.model.Investindex;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InvestindexMapper {
    int deleteByPrimaryKey(Integer indexId);

    int insert(Investindex record);

    int insertSelective(Investindex record);

    List<Investindex> selectByAreaName(Investindex investindex);

    List<Investindex> getListByAreaName(Investindex investindex);

    Investindex selectByPrimaryKey(Integer indexId);

    int updateByPrimaryKeySelective(Investindex record);

    int updateByPrimaryKey(Investindex record);
}