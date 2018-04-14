package com.calidad2018.pcc.payroll.PayrollEmployee;

import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.payroll.Payroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PayrollEmployeeServiceImpl implements PayrollEmployeeService{

    @Autowired
    private PayrollEmployeeDAO dao;

    @Override
    @Transactional
    public Iterable<PayrollEmployee> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public PayrollEmployee findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(PayrollEmployee position){
        this.dao.save(position);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }
    @Override
    @Transactional
    public Payroll findLastEmployeePayroll(Long employeeId){
        Iterable<PayrollEmployee> employeePayrolls = this.dao.findAll();
        ArrayList<Payroll> payrollArray = new ArrayList<>();
        // get all payrolls of employee
        employeePayrolls.forEach(ep -> {
            if(ep.getEmployee().getId() == employeeId){
                payrollArray.add(ep.getPayroll());
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