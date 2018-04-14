package com.calidad2018.pcc.payroll.PayrollEmployee;
import com.calidad2018.pcc.payroll.Payroll;

public interface PayrollEmployeeService {
    public Iterable<PayrollEmployee> findAll();
    public PayrollEmployee findById(Long id);
    public void save(PayrollEmployee entity);
    public void delete(Long id);
    public Payroll findLastEmployeePayroll(Long employeeId);
}