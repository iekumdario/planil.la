package com.calidad2018.pcc.position;

import com.calidad2018.pcc.employee.Employee;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Position {

    private String name;
    private String description;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Position() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}