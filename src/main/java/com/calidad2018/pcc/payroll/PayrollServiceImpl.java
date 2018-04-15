package com.calidad2018.pcc.payroll;

import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.payroll.PayrollEmployee.PayrollEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PayrollServiceImpl implements EntityService<Payroll> {

    @Autowired
    private PayrollDAO dao;

    @Override
    @Transactional
    public Iterable<Payroll> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public Payroll findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(Payroll position){
        this.dao.save(position);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }

    public List<Payroll> findByState(boolean state){

        return dao.findByState(state);
    }

    @Transactional
    public Payroll findLastEmployeePayroll(Employee employee){
        // get all payrolls of employee
        Iterable<PayrollEmployee> employeePayrolls = employee.getPayrollEmployee();
        ArrayList<Payroll> payrollArray = new ArrayList<>();
        // only add payed payrolls
        employeePayrolls.forEach(ep -> {
            Payroll p = ep.getPayroll();
            if(p.isState()){
                payrollArray.add(p);
            }
        });
        if(payrollArray.size() > 0){
            // return last payroll
            payrollArray.sort(Comparator.comparingLong(Payroll::getId).reversed());
            return payrollArray.get(0);
        }
        return null;
    }
}