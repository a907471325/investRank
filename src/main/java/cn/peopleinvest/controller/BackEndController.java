package cn.peopleinvest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackEndController {

    @RequestMapping(value = "/console")
    public String index(){

        return "backend_index";
    }

    @RequestMapping(value = "/productList")
    public String productList(){

        return "backend_product_list";
    }

    @RequestMapping(value = "/productDetail")
    public String productDetail(){

        return "backend_product_detail";
    }

    @RequestMapping(value = "/productRecycleBin")
    public String productRecycle(){

        return "backend_recycle_bin";
    }


}
