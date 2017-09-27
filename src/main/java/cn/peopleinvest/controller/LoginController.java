package cn.peopleinvest.controller;

import cn.peopleinvest.model.User;
import cn.peopleinvest.service.MailService;
import cn.peopleinvest.service.MsgService;
import cn.peopleinvest.config.ConstVal;
import cn.peopleinvest.model.Investindex;
import cn.peopleinvest.model.Loginuser;
import cn.peopleinvest.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private MsgService msgService;

    @Resource
    private MailService mailService;

    @PostMapping(value = "/loginCheck")
    @ResponseBody
    public Loginuser login(Loginuser loginuser, HttpServletRequest httpServletRequest){

        Loginuser user = userService.loginUser(loginuser);
        if (user == null){
            user = new Loginuser();
        }
        httpServletRequest.getSession().setAttribute(ConstVal.ONLINE_USER,user);
        return user;
    }

    @RequestMapping(value = "/sendMsg")
    @ResponseBody
    public String sendSMS(@RequestParam("phone") String phone, HttpSession session) throws Exception {
        String code = msgService.sendMsg(phone);
        if (StringUtils.isNotEmpty(code) && code.length()==6){
            session.setAttribute("code", code);
            return ConstVal.SUCCESS;
        }
        else return ConstVal.SEND_ERROR;
    }

    @RequestMapping(value = "/checkCode")
    @ResponseBody
    public String checkCode(@RequestParam("code") String code, HttpSession session) throws Exception {
        if (StringUtils.isNotEmpty(code) && code.length()==6){
            String codeSrc = (String)session.getAttribute("code");
            if (code.equals(codeSrc)){
                return ConstVal.SUCCESS;
            }
        }
        return ConstVal.SEND_ERROR;
    }

    @RequestMapping(value = "/checkPhone")
    @ResponseBody
    public String checkPhone(@RequestParam("phone") String phone){
        Loginuser loginuser = new Loginuser();
        loginuser.setLoginname(phone);
        Loginuser rst = userService.querySelective(loginuser);
        if (rst != null) return ConstVal.INPUT;
        return ConstVal.SUCCESS;
    }

    @PostMapping(value = "/registPhone")
    public String registPhone(Loginuser loginuser,HttpSession session){

        User user = new User();
        String id = UUID.randomUUID().toString();
        user.setId(id);
        user.setIsPush(ConstVal.IS_PUSH);
        user.setJoinTime(new Date());
        user.setRole("normal");

        userService.insertUser(user);

        loginuser.setState(ConstVal.USER_STATUS_INVALID);
        loginuser.setType("phone");
        loginuser.setUserId(id);
        userService.insertLoginUser(loginuser);//保存注册信息
        session.setAttribute(ConstVal.ONLINE_USER,loginuser);
        return "redirect:index";
    }




    @PostMapping(value = "/registConfirm")
    @ResponseBody
    public String registConfirm(Loginuser loginuser) {
        Loginuser param = new Loginuser();
        param.setLoginname(loginuser.getLoginname());
        Loginuser existUser = userService.querySelective(param);
        if (existUser != null ){
            if (existUser.getState() == 1){
                return ConstVal.INPUT;
            }
            else {
                   new Thread(()->{
                       try {
                           mailService.processregister(existUser);
                       } catch (Throwable throwable) {
                           throwable.printStackTrace();
                       }
                   }).start();

            }
        }
        else {
            new Thread(()->{
                try {
                    mailService.processregister(loginuser);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }).start();

        }
        return  ConstVal.SUCCESS;
    }

    @GetMapping(value = "/regist")
    public String regist(String email,String token){

        Loginuser loginuser = new Loginuser();
        loginuser.setLoginname(email);
        loginuser.setType("email");
        loginuser = userService.querySelective(loginuser);
        if(loginuser != null && loginuser.getLoginname().equals(email) && loginuser.getRemark().equals(token) && loginuser.getState() == 0){
            loginuser.setState(1);
            userService.updateLoginUser(loginuser);
            return "registSuccess";
        }
        return "index";
    }


    @GetMapping(value = "/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute(ConstVal.ONLINE_USER);
        return "index";
    }

    @GetMapping(value = "/logoutSearch")
    public ModelAndView logoutSearch(Investindex investindex,HttpSession httpSession, ModelMap modelMap){
        httpSession.removeAttribute(ConstVal.ONLINE_USER);
        Map map= new HashMap();
        map.put("areaName", investindex.getAreaName());
        return new ModelAndView(new RedirectView("search?"),map);
    }

}
