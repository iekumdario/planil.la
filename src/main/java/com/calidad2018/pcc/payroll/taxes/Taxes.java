package com.calidad2018.pcc.payroll.taxes;

import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;

public interface Taxes {

    double socialSecurityTax(double salary);

    double educationTax(double salary);

    double irsTax(double salary);

    PayrollTaxes payrollTaxes(double salary);
}
