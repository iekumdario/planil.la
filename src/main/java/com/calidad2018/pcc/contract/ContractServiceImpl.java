package com.calidad2018.pcc.contract;

import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractServiceImpl implements EntityService<Contract> {

    @Autowired
    private ContractDAO dao;

    @Override
    @Transactional
    public Iterable<Contract> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public Contract findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(Contract contract){
        this.dao.save(contract);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }
}