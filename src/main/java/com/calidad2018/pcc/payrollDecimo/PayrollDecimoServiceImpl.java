package com.calidad2018.pcc.payrollDecimo;

import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.payroll.Payroll;
import com.calidad2018.pcc.payroll.PayrollDAO;
import com.calidad2018.pcc.payroll.PayrollEmployee.PayrollEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PayrollDecimoServiceImpl implements EntityService<PayrollDecimo> {

    @Autowired
    private PayrollDecimoDAO dao;

    @Override
    @Transactional
    public Iterable<PayrollDecimo> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public PayrollDecimo findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(PayrollDecimo position){
        this.dao.save(position);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }

    public List<PayrollDecimo> findByState(boolean state){

        return dao.findByState(state);
    }

    public List<PayrollDecimo> findByDate(Date startDay, Date endDay){

        return dao.findByStateAndStartDateBetween(true,startDay,endDay);
    }
}