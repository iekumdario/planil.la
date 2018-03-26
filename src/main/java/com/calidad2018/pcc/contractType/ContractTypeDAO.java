package com.calidad2018.pcc.contractType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeDAO extends CrudRepository<ContractType, Long> {
}

