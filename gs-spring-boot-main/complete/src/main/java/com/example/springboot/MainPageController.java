package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/a")
class MainPageController {

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView index()
    {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("version", "0.1");
        return mav;
    }

}
