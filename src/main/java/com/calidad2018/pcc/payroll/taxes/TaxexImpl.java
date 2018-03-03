package com.calidad2018.pcc.payroll.taxes;


import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.utils.Constants;
import com.calidad2018.pcc.utils.Round;
import org.springframework.stereotype.Component;

@Component
public class TaxexImpl implements Taxes{


    @Override
    public double socialSecurityTax(double salary) {
        return Round.Round(salary* Constants.SOCIAL_SECURITY_TAX);
    }

    @Override
    public double educationTax(double salary) {
        return Round.Round(salary* Constants.EDUCATION_TAX);
    }

    @Override
    public double irsTax(double salary) {

        double annualSalary = Round.Round(salary * Constants.NUMBER_OF_PAYROLL_AT_YEAR);

        if(annualSalary <= 11000.00){

            return 0;

        }
        else if( annualSalary >= 11000 && annualSalary < 50000.00){

            double oversalary = annualSalary - 11000.01;

            double irsTax = Round.Round(oversalary * Constants.IRS_TAX_LEVEL_ONE);

            return Round.Round(irsTax / Constants.NUMBER_OF_PAYROLL_AT_YEAR);

        }
        else {

            double oversalary = annualSalary - 11000.01;

            double irsTax = Round.Round(oversalary * Constants.IRS_TAX_LEVEL_TWO);

            return Round.Round(irsTax / Constants.NUMBER_OF_PAYROLL_AT_YEAR);
        }


    }

    @Override
    public PayrollTaxes payrollTaxes(double salary) {

        PayrollTaxes payrollTaxes = new PayrollTaxes();

        payrollTaxes.setSocialSecurityTax(socialSecurityTax(salary));

        payrollTaxes.setEducationTax(educationTax(salary));

        payrollTaxes.setRentTax(irsTax(salary));

        payrollTaxes.setTotalInTax(Round.Round(payrollTaxes.getSocialSecurityTax() + payrollTaxes.getEducationTax() + payrollTaxes.getRentTax()));

        return payrollTaxes;
    }
}
