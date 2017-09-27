package cn.peopleinvest.controller;


import cn.peopleinvest.model.Loginuser;
import cn.peopleinvest.model.LoginuserKey;
import cn.peopleinvest.model.UserInfo;
import cn.peopleinvest.service.MailService;
import cn.peopleinvest.service.UserService;
import cn.peopleinvest.config.ConstVal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

    @PostMapping(value = "/changePassword")
    @ResponseBody
    public String changePassword(UserInfo userInfo){
        Loginuser loginuser = new Loginuser();
        loginuser.setLoginname(userInfo.getUsername());
        loginuser.setPassword(userInfo.getOriPassword());
        Loginuser rst = userService.loginUser(loginuser);
        if (rst == null){
            return ConstVal.INPUT;
        }
        else {
            rst.setPassword(userInfo.getNewPassword());
            userService.updateLoginUser(rst);
            return ConstVal.SUCCESS;
        }

    }

    @PostMapping(value = "addEmail")
    @ResponseBody
    public String addEmail(UserInfo userInfo,HttpSession session){
        Loginuser loginuser = new Loginuser();
        loginuser.setLoginname(userInfo.getNewEmail());
        if ( userService.querySelective(loginuser) != null){
            return ConstVal.INPUT;
        }
        Loginuser sessionUser = (Loginuser)session.getAttribute(ConstVal.ONLINE_USER);
        Loginuser rst = new Loginuser();
        rst.setLoginname(userInfo.getNewEmail());
        rst.setPassword(sessionUser.getPassword());
        rst.setUserId(sessionUser.getUserId());
        new Thread(() -> {
            try {
                mailService.processregister(rst);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }).start();
        return ConstVal.SUCCESS;
    }

    @GetMapping(value = "addPhone")
    @ResponseBody
    public String addPhone(@RequestParam(value = "phone") String phone,HttpSession session){
        Loginuser loginuser = new Loginuser();
        loginuser.setLoginname(phone);
        if ( userService.querySelective(loginuser) != null){
            return ConstVal.INPUT;
        }
        Loginuser sessionUser = (Loginuser)session.getAttribute(ConstVal.ONLINE_USER);
        Loginuser rst = new Loginuser();
        rst.setLoginname(phone);
        rst.setPassword(sessionUser.getPassword());
        rst.setUserId(sessionUser.getUserId());
        rst.setType("phone");
        rst.setState(1);
        userService.insertLoginUser(rst);
        return ConstVal.SUCCESS;
    }


    @PostMapping(value = "updateEmail")
    @ResponseBody
    public String updateEmail(UserInfo userInfo){

        Loginuser checkUser = userService.queryByPrimaryKey(new LoginuserKey("email", userInfo.getUsername()));
        if (!checkUser.getLoginname().equals(userInfo.getOriEmail()))
            return ConstVal.INPUT;
        Loginuser loginuser = new Loginuser();
        loginuser.setLoginname(userInfo.getNewEmail());
        loginuser = userService.querySelective(loginuser);
        if ( loginuser != null){
            return ConstVal.REGISTED;
        }
        checkUser.setLoginname(userInfo.getNewEmail());
        new Thread(() -> {
            try {
                mailService.processUpdate(checkUser);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }).start();
        return ConstVal.SUCCESS;
    }
}
