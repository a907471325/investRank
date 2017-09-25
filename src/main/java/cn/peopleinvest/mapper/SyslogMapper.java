package cn.peopleinvest.mapper;

import cn.peopleinvest.model.Syslog;
import org.springframework.stereotype.Component;

@Component
public interface SyslogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Syslog record);

    int insertSelective(Syslog record);

    Syslog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Syslog record);

    int updateByPrimaryKey(Syslog record);
}