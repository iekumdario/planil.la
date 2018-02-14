package com.calidad2018.pcc.employee;
import com.calidad2018.pcc.position.Position;
import com.calidad2018.pcc.contract.Contract;
import com.calidad2018.pcc.departament.Department;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Employee {

    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
