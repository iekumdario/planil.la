package com.calidad2018.pcc.payroll;

import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.employee.EmployeeService;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.payroll.PayrollEmployee.PayrollEmployee;
import com.calidad2018.pcc.payroll.PayrollEmployee.PayrollEmployeeServiceImpl;
import com.calidad2018.pcc.payroll.taxesFactory.Taxes;
import com.calidad2018.pcc.utils.Constants;
import com.calidad2018.pcc.utils.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private EmployeeService<Employee> employeeServices;

    @Autowired
    private PayrollServiceImpl payrollService;

    @Autowired
    private Taxes taxes;

    @GetMapping(value = "/create")
    public String getFullPayroll(Model model) {

        List<Employee> employees = employeeServices.findByContractType(Constants.PERMANENTE);

        List<PayrollEmployee> payrollEmployees = new ArrayList<>();

        Payroll payroll = new Payroll();

        employees.forEach(employee -> {
            boolean allowPayment;
            Payroll lastEmployeePayroll = payrollService.findLastEmployeePayroll(employee);
            if(lastEmployeePayroll == null){
                // no payrolls yet for this employee
                allowPayment = true;
            } else {
                LocalDate lastPayDate = lastEmployeePayroll.getPayDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                // check if employee has already been paid in this term (via vacations or  previous payroll)
                allowPayment = lastPayDate.compareTo(LocalDate.now().minusDays(15)) <= 0;
            }
            if(allowPayment){
                PayrollEmployee builder = getPayroll(employee,payroll);
                payrollEmployees.add(builder);
            }
        });

        if(payrollEmployees.size() == 0) {
            return "payroll/error";
        }

        model.addAttribute("employees", payrollEmployees);

        setPayrollDateModel(model);

        createAndSavePayroll(model, payrollEmployees, payroll);

        model.addAttribute("payroll", payroll);


        return "payroll/create";
    }

    @GetMapping(value = "/{employeeId}")
    public String getEmployeePayroll(Model model, @PathVariable Long employeeId) {
        getEmployeePayrollByTerm(model, employeeId);
        return "payroll/employee";
    }

    @GetMapping(value = "/vacations/{employeeId}")
    public String getEmployeeVacationsPayroll(Model model, @PathVariable Long employeeId) {
        boolean allowPayment;
        Employee employee = employeeServices.findById(employeeId);
        Payroll lastEmployeePayroll = payrollService.findLastEmployeePayroll(employee);
        if(lastEmployeePayroll == null){
            // no payrolls yet for this employee
            allowPayment = true;
        } else {
            LocalDate lastPayDate = lastEmployeePayroll.getPayDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // check if employee has already been paid in the last 15 days
            allowPayment = lastPayDate.compareTo(LocalDate.now().minusDays(15)) <= 0;
        }

        if(!allowPayment) {
            return "payroll/error";
        }

        List<PayrollEmployee> payrollEmployees = new ArrayList<>();
        PayrollEmployee payrollEmployee = getVacationEmployeePayrollByTerm(model, employeeId);
        payrollEmployees.add(payrollEmployee);
        Payroll payroll = payrollEmployee.getPayroll();
        createAndSavePayroll(model,payrollEmployees,payroll,true);
        model.addAttribute("payroll", payroll);
        return "payroll/employeeVacations";
    }

    @PostMapping(value = "/{payrollId}")
    public String upsert(@ModelAttribute Employee employee, @PathVariable Long payrollId, Model model, BindingResult bindingResult) {

        Payroll payroll = payrollService.findById(payrollId);

        payroll.setState(true);

        payrollService.save(payroll);

        return "redirect:/payroll";
    }

    @GetMapping
    public String allPayroll(Model model) {

        List<Payroll> payrolls = (List<Payroll>) payrollService.findByState(true);

        model.addAttribute("payrolls", payrolls);

        return "payroll/index";
    }

    private void createAndSavePayroll(Model model, List<PayrollEmployee> payrollEmployees, Payroll payroll) {
        createAndSavePayroll(model,payrollEmployees,payroll,false);
    }

    private void createAndSavePayroll(Model model, List<PayrollEmployee> payrollEmployees, Payroll payroll, boolean isVacation) {
        String payrollTerm = model.asMap().get("payrollTerm").toString();
        String month = model.asMap().get("currentMonth").toString();
        String payrollName = isVacation ? payrollEmployees.get(0).getEmployee().getFullName() + " vacaciones " + payrollTerm + " quincena " + month : payrollTerm + " quincena " + month;
        createAndSavePayroll(model,payrollEmployees,payroll,payrollName);
    }

    private void createAndSavePayroll(Model model, List<PayrollEmployee> payrollEmployees, Payroll payroll, String name) {
        Set<PayrollEmployee> set = new HashSet<PayrollEmployee>(payrollEmployees);

        System.out.println("Set values .....");
        for (PayrollEmployee temp : set){
            System.out.println(temp.getEmployee().getFirstName());
        }

        Calendar calendar = Calendar.getInstance();

        payroll.setName(name);

        payroll.setEndDate(calendar.getTime());

        payroll.setPayDate(calendar.getTime());

        payroll.setStartDate(calendar.getTime());

        payroll.setEmployees(set);

        payroll.setState(false);

        payrollService.save(payroll);
    }

    // este metodo llamaria con diferentes parametros a employeePayroll dependiendo del termino del pago(quincena, decimo o vacaciones)
    private PayrollEmployee getEmployeePayrollByTerm(Model model, @PathVariable Long employeeId) {
        Employee employee = employeeServices.findById(employeeId);
        PayrollEmployee employeePayroll = getPayroll(employee);

        model.addAttribute("employee", employeePayroll);
        // calculando aparte en caso de que se hagan las deducciones personales
        double totalDeductions = employeePayroll.getGrossSalary() - employeePayroll.getNetSalary();
        model.addAttribute("totalDeductions", totalDeductions);
        setPayrollDateModel(model);
        return employeePayroll;
    }

    private PayrollEmployee getVacationEmployeePayrollByTerm(Model model, @PathVariable Long employeeId) {
        //esta pantalla si permite pagar asi que debemos crear la planilla
        Payroll payroll = new Payroll();
        Employee employee = employeeServices.findById(employeeId);
        PayrollEmployee employeePayroll = getPayroll(employee,payroll);

        model.addAttribute("employee", employeePayroll);
        double totalDeductions = employeePayroll.getGrossSalary() - employeePayroll.getNetSalary();
        model.addAttribute("totalDeductions", totalDeductions);
        setPayrollDateModel(model);
        return employeePayroll;
    }

    //parametrizar para variar el termino de pago
    private PayrollEmployee getPayroll(Employee employee) {
        PayrollEmployee builder = new PayrollEmployee();

        double grossSalaryPerPayroll = Round.Round(employee.getContract().getBaseSalary() / 2);
        PayrollTaxes employeeTaxes = this.taxes.payrollTaxes(grossSalaryPerPayroll);

        employeeTaxes.setEmployee(builder);
        double netSalary = Round.Round(grossSalaryPerPayroll - employeeTaxes.getTotalInTax());

        builder.setEmployee(employee);
        builder.setGrossSalary(grossSalaryPerPayroll);
        builder.setTaxes(employeeTaxes);
        builder.setNetSalary(netSalary);

        return builder;
    }


    private PayrollEmployee getPayroll(Employee employee,Payroll payroll) {
        PayrollEmployee builder = new PayrollEmployee();

        double grossSalaryPerPayroll = Round.Round(employee.getContract().getBaseSalary() / 2);
        PayrollTaxes employeeTaxes = this.taxes.payrollTaxes(grossSalaryPerPayroll);

        employeeTaxes.setEmployee(builder);
        double netSalary = Round.Round(grossSalaryPerPayroll - employeeTaxes.getTotalInTax());

        builder.setEmployee(employee);
        builder.setGrossSalary(grossSalaryPerPayroll);
        builder.setTaxes(employeeTaxes);
        builder.setNetSalary(netSalary);
        builder.setPayroll(payroll);

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
