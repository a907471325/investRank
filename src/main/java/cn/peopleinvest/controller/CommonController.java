package cn.peopleinvest.controller;

import cn.peopleinvest.service.AreaService;
import cn.peopleinvest.service.InvestIndexService;
import cn.peopleinvest.service.UserService;
import cn.peopleinvest.config.ConstVal;
import cn.peopleinvest.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
public class CommonController {


    @Resource
    private UserService userService;

    @Resource
    private AreaService areaService;

    @Resource
    private InvestIndexService investIndexService;

    /**
     * 根据地区搜索指数
     * @param area
     * @param modelMap
     * @param session
     * @return
     */
    @RequestMapping(value = "/search")
    public String search(Investindex area,ModelMap modelMap,HttpSession session) {
        List<Investindex> indexs = investIndexService.getIndexs(area);
        if (indexs!= null&& indexs.size() > 0){
            IndexList indexList = new IndexList(indexs,area.getAreaName());
            modelMap.addAttribute("indexList",indexList);
            if (indexs.size() == 1){
                return detail(indexs.get(0),modelMap,session);
            }
        }
        return "search";
    }

    /**
     * 默认跳转到主页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String main() {
            return "index";
        }

    /**
     * 跳转到页面详情
     * @param area
     * @param modelMap
     * @param session
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Investindex area,ModelMap modelMap,HttpSession session) {
        if(session.getAttribute(ConstVal.ONLINE_USER)==null)
            return "index";
        modelMap.addAttribute("searchInfo",area);
        return "detail";
    }


    @RequestMapping(value = "/reqData")
    @ResponseBody
    public List<Investindex> resustResponse(String areaName) {
        Investindex investindex = new Investindex();
        investindex.setAreaName(areaName);
        return investIndexService.getResultList(investindex);
    }

    @RequestMapping(value = "/reqArea")
    @ResponseBody
    public List<Area> reqArea(String areaName) {
        return areaService.queryByPrimarykey(areaName);
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(HttpSession session,ModelMap modelMap){
        Map<String, String> params = new HashMap<>();
        Loginuser loginuser = (Loginuser)session.getAttribute(ConstVal.ONLINE_USER);
        if (loginuser == null) return "redirect:index";
        Loginuser email = userService.queryByUnionId(new LoginuserKey("email", loginuser.getUserId()));
        Loginuser phone = userService.queryByUnionId(new LoginuserKey("phone", loginuser.getUserId()));
        Loginuser weixin = userService.queryByUnionId(new LoginuserKey("weixin", loginuser.getUserId()));
        params.put("email",email==null?null:email.getLoginname());
        params.put("phone",phone==null?null:phone.getLoginname());
        params.put("weixin",weixin==null?null:weixin.getLoginname());
        params.put("password",loginuser.getPassword());
        params.put("isPush","1");
        modelMap.addAttribute("userInfo",params);
        return "userInfo";
    }


    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "getVercode")
    public void getVercode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(200, 150, 255);
        g.setColor(c);
        g.fillRect(0, 0, 68, 22);

        char[] ch = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789".toCharArray();
        Random r = new Random();
        int len = ch.length, index;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            g.drawString(ch[index]+"", (i*15)+3, 18);
            sb.append(ch[index]);
        }
        request.getSession().setAttribute("piccode", sb.toString());
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    @RequestMapping(value = "getCodeSession")
    @ResponseBody
    public String getCodeSession(String Date,HttpSession session){
        return (String)session.getAttribute("piccode");
    }




}
