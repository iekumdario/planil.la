package com.calidad2018.pcc.home;

import com.calidad2018.pcc.employee.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
       public String home(Model model) {



        return "home/noEmployee";
    }




}

