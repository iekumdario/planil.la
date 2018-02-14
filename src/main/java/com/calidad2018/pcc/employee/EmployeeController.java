package com.calidad2018.pcc.employee;

import com.calidad2018.pcc.position.PositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private PositionDAO positionRepo;

    @GetMapping("/employee")
    public String employeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("availablePositions", positionRepo.findAll());

        return "employee/employee";
    }

    @PostMapping("/employee")
    public String employeeSubmit(@ModelAttribute Employee employee) {
        return "home/index";
    }
}
