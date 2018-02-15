package com.calidad2018.pcc.employee;

import com.calidad2018.pcc.contract.Contract;
import com.calidad2018.pcc.contracttype.ContractType;
import com.calidad2018.pcc.contracttype.ContractTypeDAO;
import com.calidad2018.pcc.departament.DepartamentDAO;
import com.calidad2018.pcc.departament.Department;
import com.calidad2018.pcc.position.PositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Controller
public class EmployeeController {

    @Autowired
    private PositionDAO positionRepo;

    @Autowired
    private ContractTypeDAO contractTypeRepo;

    @Autowired
    private DepartamentDAO departmentRepo;

    @GetMapping("/employee")
    public String employeeForm(Model model) {
        Employee newEmployee = new Employee();
        Contract newContract = new Contract();
        newContract.setContractType(new ContractType());
        newEmployee.setContract(newContract);
        newEmployee.setDepartment(new Department());
        model.addAttribute("employee", newEmployee);
        model.addAttribute("availablePositions", positionRepo.findAll());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("contractTypes", contractTypeRepo.findAll());
        model.addAttribute("departments", departmentRepo.findAll());
        List<Country> countries =  new ArrayList<>();

        for (String country : Locale.getISOCountries()) {
            Locale locale = new Locale("", country);
            countries.add(new Country(locale.getISO3Country().toUpperCase(), locale.getDisplayName()));
        }

        countries.sort(Comparator.comparing(Country::getName));

        model.addAttribute("countries",countries.toArray());

        return "employee/employee";
    }

    @PostMapping("/employee")
    public String employeeSubmit(@ModelAttribute Employee employee, Model model, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            model.addAttribute("employee", employee);
            return "home/index";
        }
        return "error";
    }
}
