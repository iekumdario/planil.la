package com.calidad2018.pcc.payroll.PayRollTaxes;

import com.calidad2018.pcc.payroll.Payroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollTaxesDAO extends CrudRepository<PayrollTaxes, Long> {
}
