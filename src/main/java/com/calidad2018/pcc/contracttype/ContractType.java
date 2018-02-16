package com.calidad2018.pcc.contracttype;

import com.calidad2018.pcc.contract.Contract;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ContractType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "contractType", cascade = CascadeType.ALL)
    private Set<Contract> contracts;

    public ContractType(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
}
