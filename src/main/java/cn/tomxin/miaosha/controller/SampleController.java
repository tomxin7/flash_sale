package cn.tomxin.miaosha.controller;

import cn.tomxin.miaosha.domain.User;
import cn.tomxin.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","tomxin");
        return "hello";
    }

    @RequestMapping("/db")
    @ResponseBody
    public User db(){

        return userService.getById(1);
    }
}
