package com.calidad2018.pcc.payroll.PayrollEmployee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollEmployeeDAO extends CrudRepository<PayrollEmployee, Long> {
}
