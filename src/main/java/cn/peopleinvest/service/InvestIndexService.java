package cn.peopleinvest.service;

import cn.peopleinvest.model.Area;
import cn.peopleinvest.model.Investindex;

import java.util.List;

/**
 * Created by 90747 on 2017/8/9.
 */
public interface InvestIndexService {

    List<Investindex> getIndexs(Investindex investindex);

    List<Investindex> getResultList(Investindex investindex);

}
