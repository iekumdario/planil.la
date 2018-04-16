package com.calidad2018.pcc.creditor;

import com.calidad2018.pcc.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Creditor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private Double amount;

    @NotNull
    private int payments = 0;

    @NotNull
    private int paymentsMade = 0;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Creditor(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getPayments() {
        return payments;
    }

    public int getPaymentsMade() {
        return paymentsMade;
    }

    public void setPaymentsMade(int paymentsMade) {
        this.paymentsMade = paymentsMade;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

}
