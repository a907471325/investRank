package cn.peopleinvest.exception;

/**
 * Created by 90747 on 2017/8/10.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg){
        super(msg);
    }
}
