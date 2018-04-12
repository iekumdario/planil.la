package com.calidad2018.pcc.payroll.PayrollEmploy;

import com.calidad2018.pcc.core.EntityService;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxes;
import com.calidad2018.pcc.payroll.PayRollTaxes.PayrollTaxesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayrollEmployeeServiceImpl implements EntityService<PayrollEmployee> {

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
}