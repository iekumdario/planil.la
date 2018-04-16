package com.calidad2018.pcc.creditor;

import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.employee.EmployeeService;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.payroll.taxesFactory.Taxes;
import com.calidad2018.pcc.utils.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/creditor")
public class CreditorController {
    @Autowired
    private EmployeeService<Employee> employeeService;

    @Autowired
    private EntityService<Creditor> creditorService;

    @Autowired
    private Taxes taxes;

    @GetMapping(value = "/new/{employeeId}")
    public String create(Model model, @PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        Creditor creditor = new Creditor();
        creditor.setEmployee(employee);
        model.addAttribute("creditor", creditor);
        double netSalary = getNetSalary(employee);
        model.addAttribute("maxAmount", netSalary);
        model.addAttribute("isNew",true);

        return "creditor/index";
    }

    private double getNetSalary(Employee employee) {
        double grossSalary = Round.Round(employee.getContract().getBaseSalary() / 2);
        PayrollTaxes employeeTaxes = this.taxes.payrollTaxes(grossSalary);
        double otherDebt = 0;
        for(Creditor c: employee.getCreditors()) {
            otherDebt += c.getAmount();
        }
        return Round.Round(grossSalary - employeeTaxes.getTotalInTax() - otherDebt);
    }

    @GetMapping(value = "/{creditorId}")
    public String edit(Model model, @PathVariable Long creditorId) {
        Creditor creditor = creditorService.findById(creditorId);
        model.addAttribute("creditor", creditor);
        model.addAttribute("isNew",false);

        return "creditor/index";
    }

    @PostMapping
    public String save(@ModelAttribute Creditor creditor, Model model, BindingResult bindingResult) {

        if(!bindingResult.hasErrors()){
            model.addAttribute("creditor", creditor);
            System.out.printf(creditor.toString());
            creditorService.save(creditor);
            return "redirect:/";
        }
        return "error";
    }

    @DeleteMapping(value = "/{creditorId}")
    public String delete(@PathVariable Long creditorId) {
        try {
            creditorService.delete(creditorId);
            return "redirect:/";
        } catch(Exception e) {
            return "error";
        }
    }

}
