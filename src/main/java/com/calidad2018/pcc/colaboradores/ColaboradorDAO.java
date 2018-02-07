package com.calidad2018.pcc.colaboradores;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorDAO extends CrudRepository<Colaborador, Long> {
}
