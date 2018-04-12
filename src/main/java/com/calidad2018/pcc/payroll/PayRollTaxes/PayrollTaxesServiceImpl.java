package com.calidad2018.pcc.payroll.PayRollTaxes;

import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayrollTaxesServiceImpl implements EntityService<PayrollTaxes> {

    @Autowired
    private PayrollTaxesDAO dao;

    @Override
    @Transactional
    public Iterable<PayrollTaxes> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public PayrollTaxes findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(PayrollTaxes position){
        this.dao.save(position);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }
}