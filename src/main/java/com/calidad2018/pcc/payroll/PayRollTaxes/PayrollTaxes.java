package com.calidad2018.pcc.payroll.PayRollTaxes;

import com.calidad2018.pcc.core.BaseEntityId;
import com.calidad2018.pcc.payroll.PayrollEmploy.PayrollEmployee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;



@Entity(name = "payrollTaxes")
public class PayrollTaxes extends BaseEntityId{


    private Double socialSecurityTax;

    private Double educationTax;

    private Double rentTax;

    private Double totalInTax;

    @OneToOne
    private PayrollEmployee employee;

    public PayrollTaxes() {
        super();
    }

    public Double getSocialSecurityTax() {
        return socialSecurityTax;
    }

    public void setSocialSecurityTax(Double socialSecurityTax) {
        this.socialSecurityTax = socialSecurityTax;
    }

    public Double getEducationTax() {
        return educationTax;
    }

    public void setEducationTax(Double educationTax) {
        this.educationTax = educationTax;
    }

    public Double getRentTax() {
        return rentTax;
    }

    public void setRentTax(Double rentTax) {
        this.rentTax = rentTax;
    }

    public Double getTotalInTax() {
        return totalInTax;
    }

    public void setTotalInTax(Double totalInTax) {
        this.totalInTax = totalInTax;
    }

    public PayrollEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(PayrollEmployee employee) {
        this.employee = employee;
    }
}
