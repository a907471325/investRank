package cn.peopleinvest.mapper;

import cn.peopleinvest.model.Area;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaMapper {
    int deleteByPrimaryKey(String areaName);

    int insert(Area record);

    int insertSelective(Area record);

    List<Area> selectByPrimaryKey(String areaName);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}