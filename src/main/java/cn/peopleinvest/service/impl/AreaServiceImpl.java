package cn.peopleinvest.service.impl;

import cn.peopleinvest.mapper.AreaMapper;
import cn.peopleinvest.model.Area;
import cn.peopleinvest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {


    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> queryByPrimarykey(String areaName) {
        return areaMapper.selectByPrimaryKey(areaName);
    }
}
