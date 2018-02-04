package com.calidad2018.pcc.colaboradores;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Colaborador {


    private String name;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    public Colaborador() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
