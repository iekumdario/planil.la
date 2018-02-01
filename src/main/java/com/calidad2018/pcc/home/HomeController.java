package com.calidad2018.pcc.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model) {


        return "home/index";
    }

}
