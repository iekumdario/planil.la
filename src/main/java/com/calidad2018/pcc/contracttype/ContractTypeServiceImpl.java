package com.calidad2018.pcc.contracttype;

import com.calidad2018.pcc.contracttype.ContractTypeDAO;
import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractTypeServiceImpl implements EntityService<ContractType> {

    @Autowired
    private ContractTypeDAO dao;

    @Override
    @Transactional
    public Iterable<ContractType> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public ContractType findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(ContractType contractType){
        this.dao.save(contractType);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }
}