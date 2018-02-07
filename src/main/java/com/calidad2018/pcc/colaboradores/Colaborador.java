package com.calidad2018.pcc.colaboradores;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Colaborador {


    private String name;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Colaborador() {
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
}
