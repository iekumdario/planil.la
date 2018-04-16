package com.calidad2018.pcc.creditor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditorDAO extends CrudRepository<Creditor, Long> {
}