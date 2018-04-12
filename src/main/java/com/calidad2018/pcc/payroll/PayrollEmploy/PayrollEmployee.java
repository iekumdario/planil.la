package com.calidad2018.pcc.payroll.PayrollEmploy;

import com.calidad2018.pcc.core.BaseEntityId;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.payroll.Payroll;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "payrollemployee")
public class PayrollEmployee  extends BaseEntityId{


    @ManyToOne
    private Employee employee;

    private Double grossSalary;

    private Double netSalary;

    @ManyToOne
    private Payroll payroll;

    @OneToOne (mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private PayrollTaxes taxes;


    public PayrollEmployee() {
        super();
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
}
