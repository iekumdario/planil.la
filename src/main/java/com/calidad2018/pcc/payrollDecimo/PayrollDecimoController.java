package com.calidad2018.pcc.payrollDecimo;

import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.creditor.Creditor;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.employee.EmployeeService;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxesServiceImpl;
import com.calidad2018.pcc.payroll.Payroll;
import com.calidad2018.pcc.payroll.PayrollEmployee.PayrollEmployee;
import com.calidad2018.pcc.payroll.PayrollEmployee.PayrollEmployeeServiceImpl;
import com.calidad2018.pcc.payroll.PayrollServiceImpl;
import com.calidad2018.pcc.payroll.taxesFactory.Taxes;
import com.calidad2018.pcc.utils.Constants;
import com.calidad2018.pcc.utils.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/payroll-decimo")
public class PayrollDecimoController {

    @Autowired
    private EmployeeService<Employee> employeeServices;

    @Autowired
    private PayrollServiceImpl payrollService;

    @Autowired
    private EntityService<Creditor> creditorService;

    @Autowired
    private Taxes taxes;

    @Autowired
    private PayrollDecimoServiceImpl payrollDecimoService;

    @Autowired
    private PayrollTaxesServiceImpl taxesService;

    @Autowired
    private PayrollEmployeeServiceImpl payrollEmployeeService;

    @GetMapping(value = "/create")
    public String getFullPayroll(Model model,@RequestParam String type) {

        String typeEmployee = "";
        if(type.equals("temporal")){

            typeEmployee = Constants.TEMPORAL;

        }
        else {
            typeEmployee = Constants.PERMANENTE;

        }

        String period = checkPeriod();

        Map<String,Date> dates = getDates(period);

        List<Employee> employees = employeeServices.findByContractType(typeEmployee);

        List<Payroll> paidPayroll = payrollService.findByDate(dates.get(Constants.START_DATE),dates.get(Constants.END_DATE));

        PayrollDecimo payrollDecimo = new PayrollDecimo();

        Calendar cal = Calendar.getInstance();

        payrollDecimo.setName(period.toUpperCase() + " DECIMO - " + typeEmployee.toUpperCase() + " - " + cal.get(Calendar.YEAR));

        payrollDecimo.setStartDate(dates.get(Constants.START_DATE));

        payrollDecimo.setEndDate(dates.get(Constants.END_DATE));

        payrollDecimo.setPayDate(cal.getTime());

        payrollDecimo.setState(false);

        payrollDecimoService.save(payrollDecimo);

        List<PayrollEmployee> paidEmployee = new ArrayList<>();

        employees.forEach(employee -> {

            final Double[] decimoPay = {0.0};

            paidPayroll.forEach(payroll -> {

                List<PayrollEmployee> payrollEmployees = new ArrayList<>(payroll.getEmployees());

                for(int i = 0 ; i < payrollEmployees.size(); i++){

                    if(payrollEmployees.get(i).getEmployee().getIdDocument().equals(employee.getIdDocument())){

                        decimoPay[0] += payrollEmployees.get(i).getGrossSalary();

                        break;

                    }

                }

            });

            PayrollEmployee payrollEmployee = getPayroll(employee,payrollDecimo,Round.Round(decimoPay[0] / 12));

            payrollEmployeeService.save(payrollEmployee);

            taxesService.save(payrollEmployee.getTaxes());

            paidEmployee.add(payrollEmployee);


        });

        Set<PayrollEmployee> set = new HashSet<PayrollEmployee>(paidEmployee);

        payrollDecimo.setEmployees(set);

        payrollDecimoService.save(payrollDecimo);

        model.addAttribute("employees", paidEmployee);

        model.addAttribute("payroll", payrollDecimo);

        return "payroll/create-decimo";
    }

    @RequestMapping(value = "/show/{id}")
    public String showPayroll(Model model,@PathVariable Long id) {

        PayrollDecimo payroll = payrollDecimoService.findById(id);

        List<PayrollEmployee> payrollEmployees = new ArrayList<>(payroll.getEmployees());

        payrollEmployees.forEach(payrollEmployee -> {

            System.out.println(payrollEmployee.getGrossSalary() + " Gross");

        });

        model.addAttribute("employees", payrollEmployees);

        model.addAttribute("payroll", payroll);

        return "payroll/detail";
    }


    @PostMapping(value = "/{payrollId}")
    public String upsert(@ModelAttribute Employee employee, @PathVariable Long payrollId, Model model, BindingResult bindingResult) {

        PayrollDecimo payroll = payrollDecimoService.findById(payrollId);

        payroll.setState(true);

        payrollDecimoService.save(payroll);

        return "redirect:/payroll";
    }




    private PayrollEmployee getPayroll(Employee employee,PayrollDecimo payroll,Double decimo) {

        PayrollEmployee builder = new PayrollEmployee(employee, payroll);

        double grossSalaryPerPayroll = decimo;

        PayrollTaxes employeeTaxes = this.taxes.payrollTaxesDecimo(grossSalaryPerPayroll);

        employeeTaxes.setEmployee(builder);

        double netSalary = Round.Round(grossSalaryPerPayroll - employeeTaxes.getTotalInTax() - builder.getCreditorDebt());

        builder.setGrossSalary(grossSalaryPerPayroll);

        builder.setTaxes(employeeTaxes);

        builder.setNetSalary(netSalary);

        return builder;
    }


    public String checkPeriod() {

        Calendar cal = Calendar.getInstance();

        Date time = cal.getTime();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,0);

        calendar.set(Calendar.MINUTE,0);

        calendar.set(Calendar.SECOND,0);

        calendar.set(Calendar.MILLISECOND,0);

        calendar.set(Calendar.DAY_OF_MONTH,15);

        calendar.add(Calendar.YEAR,-1);

        calendar.set(Calendar.MONTH,11);

        Date firstDate = calendar.getTime();

        calendar.add(Calendar.MONTH,4);

        Date secondDate = calendar.getTime();

        calendar.add(Calendar.MONTH,4);

        Date thirdDate = calendar.getTime();

        calendar.add(Calendar.MONTH,4);

        Date fourDate = calendar.getTime();

        String period = "";

        if(time.after(firstDate) && time.before(secondDate)){

            period = Constants.PRIMER_DECIMO;

        }
        else if(time.after(secondDate) && time.before(thirdDate)) {

            period = Constants.SEGUNDO_DECIMO;

        }

        else if(time.after(thirdDate) && time.before(fourDate)){

            period = Constants.TERCER_DECIMO;

        }


        return period;
    }

    public Map<String, Date> getDates(String period) {

        Map<String,Date> dateMap = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,0);

        calendar.set(Calendar.MINUTE,0);

        calendar.set(Calendar.SECOND,0);

        calendar.set(Calendar.MILLISECOND,0);

        calendar.set(Calendar.DAY_OF_MONTH,15);


        if(period.equals(Constants.PRIMER_DECIMO)){

                calendar.add(Calendar.YEAR,-1);

                calendar.set(Calendar.MONTH,11);

                dateMap.put(Constants.START_DATE,calendar.getTime());

                calendar.add(Calendar.MONTH,4);

                dateMap.put(Constants.END_DATE,calendar.getTime());

        }
        else if(period.equals(Constants.SEGUNDO_DECIMO)){

            calendar.set(Calendar.MONTH,3);

            dateMap.put(Constants.START_DATE,calendar.getTime());

            calendar.add(Calendar.MONTH,4);

            dateMap.put(Constants.END_DATE,calendar.getTime());

        }else {

            calendar.set(Calendar.MONTH,7);

            dateMap.put(Constants.START_DATE,calendar.getTime());

            calendar.add(Calendar.MONTH,4);

            dateMap.put(Constants.END_DATE,calendar.getTime());

        }

        return dateMap;

    }


}
