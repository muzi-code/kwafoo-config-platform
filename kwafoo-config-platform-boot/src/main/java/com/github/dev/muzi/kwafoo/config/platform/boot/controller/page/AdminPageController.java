package com.github.dev.muzi.kwafoo.config.platform.boot.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页页面接口等Page接口
 * Create by Muzi Li on 2019-09-12
 */
@Controller
@RequestMapping(value = "/", method = {RequestMethod.GET})
public class AdminPageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPageController.class);


    @RequestMapping("/admin")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/loginPage")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-login");
        return modelAndView;
    }

}
