package cn.peopleinvest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 小程序后台控制器
 */
@Controller
public class BackEndController {

    /**
     * 后台首页
     * @return
     */
    @RequestMapping(value = "/console")
    public String index(){

        return "backend_index";
    }

    /**
     * 后台产品列表
     * @return
     */
    @RequestMapping(value = "/productList")
    public String productList(){

        return "backend_product_list";
    }

    /**
     * 商品详情
     * @return
     */
    @RequestMapping(value = "/productDetail")
    public String productDetail(){

        return "backend_product_detail";
    }

    /**
     * 商品回收站
     * @return
     */
    @RequestMapping(value = "/productRecycleBin")
    public String productRecycle(){

        return "backend_recycle_bin";
    }


}
