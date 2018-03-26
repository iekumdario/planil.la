package com.calidad2018.pcc.employee;

import com.calidad2018.pcc.contract.Contract;
import com.calidad2018.pcc.contractType.ContractType;
import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.department.Department;
import com.calidad2018.pcc.position.Position;
import com.calidad2018.pcc.utils.Country;
import com.calidad2018.pcc.utils.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EntityService<Position> positionRepo;

    @Autowired
    private EntityService<ContractType> contractTypeRepo;

    @Autowired
    private EntityService<Department> departmentRepo;

    @Autowired
    private EmployeeService<Employee> employeeRepo;

    @Autowired
    private EntityService<Contract> contractRepo;

    @GetMapping(value = "/{userId}")
    public String updateForm(Model model, @PathVariable Long userId) {
        Employee employee = employeeRepo.findById(userId);
        model.addAttribute("employee", employee);
        model.addAttribute("isNew",false);
        setupFormModel(model);

        return "employee/form";
    }


    @GetMapping
    public String newForm(Model model) {
        Employee newEmployee = new Employee();
        Contract newContract = new Contract();
        newContract.setContractType(new ContractType());
        newEmployee.setContract(newContract);
        newEmployee.setDepartment(new Department());
        model.addAttribute("employee", newEmployee);
        model.addAttribute("isNew",true);
        setupFormModel(model);

        return "employee/form";
    }

    @PostMapping
    public String upsert(@ModelAttribute Employee employee, Model model, BindingResult bindingResult) {

        if(!bindingResult.hasErrors()){
            model.addAttribute("employee", employee);
            System.out.printf(employee.toString());
            contractRepo.save(employee.getContract());
            employeeRepo.save(employee);

            return "redirect:/";
        }
        return "error";
    }

    @DeleteMapping(value = "/{userId}")
    public String delete(@PathVariable Long userId) {
        try{
            employeeRepo.delete(userId);
            return "redirect:/";
        }catch(Exception e){
            return "error";
        }
    }

    private void setupFormModel(Model model){
        model.addAttribute("availablePositions", positionRepo.findAll());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("contractTypes", contractTypeRepo.findAll());
        model.addAttribute("departments", departmentRepo.findAll());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String maxDOB = LocalDateTime.now().minusYears(18).format(formatter);
        String minDOB = LocalDateTime.now().minusYears(62).format(formatter);
        model.addAttribute("maxDOB", maxDOB);
        model.addAttribute("minDOB", minDOB);

        List<Country> countries =  new ArrayList<>();

        for (String country : Locale.getISOCountries()) {
            Locale locale = new Locale("", country);
            countries.add(new Country(locale.getISO3Country().toUpperCase(), locale.getDisplayName()));
        }

        countries.sort(Comparator.comparing(Country::getName));
        model.addAttribute("countries",countries.toArray());
    }
}
