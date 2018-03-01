package com.calidad2018.pcc.home;

import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.employee.EmployeeServiceImpl;
import com.calidad2018.pcc.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping("/")
    public String home(Model model, Pageable pageable) {

        Page<Employee> employeeList = employeeService.findAll(Constants.PERMANENTE,pageable);

        System.out.println(employeeList);

        if (employeeList.getContent().size() == 0) {


            return "home/noEmployee";


        }

        model.addAttribute("employees", employeeList.getContent());

        return "home/index";


    }

    @RequestMapping("/temporary-employee")
    public String temporalEmployyee(Model model, Pageable pageable) {

        Page<Employee> employeeList = employeeService.findAll(Constants.TEMPORAL,pageable);

        System.out.println(employeeList);

        if (employeeList.getContent().size() == 0) {


            return "home/noEmployee";


        }

        model.addAttribute("employees", employeeList.getContent());

        return "home/temporalyEmployee";


    }

}

