package cn.peopleinvest.config;

/**
 * 微信小程序支付所需参数
 */
public class Constant {

    public static final String DOMAIN = "https://minidisk.cn";

    public static final String APP_ID = "wx291758036acbb3f6";

    public static final String APP_SECRET = "d278ace781eadade7aad50387eee042a";

    public static final String APP_KEY = "d278ace781eadade7aad50387eee042b";

    public static final String MCH_ID = "1488808042";  //商户号

    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String URL_NOTIFY = Constant.DOMAIN;
//            + "/wxpay/views/payInfo.jsp";

    public static final String TIME_FORMAT = "yyyyMMddHHmmss";

    public static final int TIME_EXPIRE = 2;  //单位是day

}
