package com.calidad2018.pcc.core;

import javax.persistence.*;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class BaseEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
