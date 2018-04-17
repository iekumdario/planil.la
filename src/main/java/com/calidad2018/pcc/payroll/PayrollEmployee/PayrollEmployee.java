package com.calidad2018.pcc.payroll.PayrollEmployee;

import com.calidad2018.pcc.core.BaseEntityId;
import com.calidad2018.pcc.creditor.Creditor;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.payroll.Payroll;
import com.calidad2018.pcc.payrollDecimo.PayrollDecimo;

import javax.persistence.*;


@Entity(name = "payrollemployee")
public class PayrollEmployee  extends BaseEntityId{


    @ManyToOne
    private Employee employee;

    private Double grossSalary;

    private Double netSalary;

    private Double creditorDebt;

    @ManyToOne
    private Payroll payroll;

    @ManyToOne
    private PayrollDecimo payrollDecimo;

    @OneToOne (mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private PayrollTaxes taxes;


    public PayrollEmployee() {
        super();
    }

    public PayrollEmployee(Employee employee) {
        super();
        this.employee = employee;
        this.creditorDebt = getTotalCreditorDebt(employee);
    }

    public PayrollEmployee(Employee employee, Payroll payroll) {
        super();
        this.employee = employee;
        this.payroll = payroll;
        this.creditorDebt = getTotalCreditorDebt(employee);
    }

    public PayrollEmployee(Employee employee, PayrollDecimo payroll) {
        super();
        this.employee = employee;
        this.payrollDecimo = payroll;
        this.creditorDebt = getTotalCreditorDebt(employee);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    public PayrollTaxes getTaxes() {
        return taxes;
    }

    public void setTaxes(PayrollTaxes taxes) {
        this.taxes = taxes;
    }

    public Double getCreditorDebt() {
        return creditorDebt;
    }

    public void setCreditorDebt(Double creditorDebt) {
        this.creditorDebt = creditorDebt;
    }

    private double getTotalCreditorDebt(Employee employee){
        double otherDebt = 0;
        for(Creditor c: employee.getCreditors()) {
            if(c.getPaymentsMade() < c.getPayments()){
                otherDebt += c.getAmount();
            }
        }
        return otherDebt;
    }

    public PayrollDecimo getPayrollDecimo() {
        return payrollDecimo;
    }

    public void setPayrollDecimo(PayrollDecimo payrollDecimo) {
        this.payrollDecimo = payrollDecimo;
    }
}
