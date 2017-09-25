package cn.peopleinvest.service;

import cn.peopleinvest.model.Area;

import java.util.List;

public interface AreaService {
    List<Area> queryByPrimarykey(String areaName);
}
