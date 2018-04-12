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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private EmployeeService<Employee> employeeServices;

    @Autowired
    private Taxes taxes;

    @GetMapping
    public String getFullPayroll(Model model) {

        List<Employee> employees = employeeServices.findByContractType(Constants.PERMANENTE);

        List<PayrollEmployee> payrollEmployees = new ArrayList<>();

        employees.forEach(employee -> {
            PayrollEmployee builder = getPayroll(employee);
            payrollEmployees.add(builder);
        });

        model.addAttribute("employees", payrollEmployees);
        setPayrollDateModel(model);

        return "payroll/index";
    }

    @GetMapping(value = "/{employeeId}")
    public String getEmployeePayroll(Model model, @PathVariable Long employeeId) {
        getEmployeePayrollByTerm(model, employeeId);
        return "payroll/employee";
    }

    @GetMapping(value = "/vacations/{employeeId}")
    public String getEmployeeVacationsPayroll(Model model, @PathVariable Long employeeId) {
        getEmployeePayrollByTerm(model, employeeId);
        return "payroll/employeeVacations";
    }

    // este metodo llamaria con diferentes parametros a employeePayroll dependiendo del termino del pago(quincena, decimo o vacaciones)
    private void getEmployeePayrollByTerm(Model model, @PathVariable Long employeeId) {
        Employee employee = employeeServices.findById(employeeId);
        PayrollEmployee employeePayroll = getPayroll(employee);

        model.addAttribute("employee", employeePayroll);
        // calculando aparte en caso de que se hagan las deducciones personales
        double totalDeductions = employeePayroll.getGrossSalary() - employeePayroll.getNetSalary();
        model.addAttribute("totalDeductions", totalDeductions);
        setPayrollDateModel(model);
    }

    //parametrizar para variar el termino de pago
    private PayrollEmployee getPayroll(Employee employee) {
        PayrollEmployee builder = new PayrollEmployee();

        double grossSalaryPerPayroll = Round.Round(employee.getContract().getBaseSalary() / 2);
        PayrollTaxes employeeTaxes = this.taxes.payrollTaxes(grossSalaryPerPayroll);
        double netSalary = Round.Round(grossSalaryPerPayroll - employeeTaxes.getTotalInTax());

        builder.setEmployee(employee);
        builder.setGrossSalary(grossSalaryPerPayroll);
        builder.setTaxes(employeeTaxes);
        builder.setNetSalary(netSalary);

        return builder;
    }

    private void setPayrollDateModel(Model model) {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("es", "ES"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String currentDate = LocalDateTime.now().format(formatter);
        String currentMonth = LocalDateTime.now().format(monthFormatter);
        String payrollTerm = LocalDateTime.now().getDayOfMonth() > 15 ? "2da" : "1era";

        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("payrollTerm", payrollTerm);
        model.addAttribute("currentDate", currentDate);
    }


}
