package com.masamiaoi.email.rest;

import com.masamiaoi.email.service.MailService;
import com.masamiaoi.email.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: MASAMIAOI
 * @description: thymeleaf 模板测试
 * @date: 2023/3/17 14:37
 * @version: 1.0.0
 */
@Controller
@RequestMapping(value = "/thymeleaf")
public class TemplateRest {

    /**
     * 邮件service
     */
    @Autowired
    MailService mailService;


    /**
     * 单个对象展示
     */
    @RequestMapping("/userPO")
    public ModelAndView userPO() {
        UserVO user = new UserVO("tom", "female", "17788996600");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userPO");
        return modelAndView;
    }

    /**
     * 对象列表循环展示
     */
    @RequestMapping("/userPOList")
    public ModelAndView userPOList() {
        List<UserVO> userList = new ArrayList<>();
        UserVO user = new UserVO("tom", "female", "17788996600");
        userList.add(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("userPOList");
        return modelAndView;
    }


    /**
     * 发送邮件的主界面
     */
    @GetMapping("/sendMail")
    public ModelAndView index() {
        //打开发送邮件的页面
        ModelAndView mv = new ModelAndView();
        //邮件发信人
        mv.addObject("from", mailService.getMailSendFrom());
        // 页面跳转
        mv.setViewName("sendMail");
        return mv;
    }


}
