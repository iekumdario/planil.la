package com.calidad2018.pcc.contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractDAO extends CrudRepository<Contract, Long> {
}


