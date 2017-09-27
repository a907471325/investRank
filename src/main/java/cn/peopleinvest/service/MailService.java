package cn.peopleinvest.service;

import cn.peopleinvest.model.Loginuser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

public interface MailService {
    @Transactional
    void processregister(Loginuser loginuser) throws Throwable;

    @Async
    @Transactional
    void processUpdate(Loginuser loginuser) throws Throwable;
}
