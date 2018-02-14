package com.calidad2018.pcc.home;

import com.calidad2018.pcc.employee.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
       public String home(Model model) {

//        Employee employee = new Employee();
//
//        employee.setId(123l);
//
//        employee.setName("Esteban");
//
//        model.addAttribute("colaborador", employee);

        return "home/index";
    }




}

