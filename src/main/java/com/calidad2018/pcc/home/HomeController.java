package com.calidad2018.pcc.home;

import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.employee.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping("/")
    public String home(Model model) {

        List<Employee> employeeList = (List<Employee>) employeeService.findAll();

        if (employeeList.size() == 0) {


            return "home/noEmployee";


        }

        model.addAttribute("employees", employeeList);

        return "home/index";


    }


}

