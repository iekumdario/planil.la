package com.calidad2018.pcc.payroll;

import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.employee.EmployeeService;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.payroll.PayrollEmploy.PayrollEmployee;
import com.calidad2018.pcc.payroll.taxes.Taxes;
import com.calidad2018.pcc.utils.Constants;
import com.calidad2018.pcc.utils.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PayrollController {

    @Autowired
    private EmployeeService<Employee> employeeServices;

    @Autowired
    private Taxes taxes;




    @GetMapping("/payroll")
    public String employeeForm(Model model) {

        List<Employee> employees = employeeServices.findByContractType(Constants.PERMANENTE);

        List<PayrollEmployee> payrollEmployees = new ArrayList<>();

        employees.forEach(employee -> {


            double grossSalaryPerPayroll = Round.Round(employee.getContract().getBaseSalary()/2);

            PayrollTaxes employeeTaxes = this.taxes.payrollTaxes(grossSalaryPerPayroll);

            double netSalary = Round.Round(grossSalaryPerPayroll - employeeTaxes.getTotalInTax());


            PayrollEmployee  builder = new PayrollEmployee();

            builder.setEmployee(employee);
            builder.setGrossSalary(grossSalaryPerPayroll);
            builder.setTaxes(employeeTaxes);
            builder.setNetSalary(netSalary);

            payrollEmployees.add(builder);




        });

        model.addAttribute("employees", payrollEmployees);

        return "payroll/index";
    }


}
