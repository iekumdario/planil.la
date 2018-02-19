package com.calidad2018.pcc.payroll;

import com.calidad2018.pcc.contract.Contract;
import com.calidad2018.pcc.contracttype.ContractType;
import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.department.Department;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.position.Position;
import com.calidad2018.pcc.utils.Country;
import com.calidad2018.pcc.utils.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Controller
public class PayrollController {


    @GetMapping("/payroll")
    public String employeeForm(Model model) {


        return "payroll/index";
    }


}
