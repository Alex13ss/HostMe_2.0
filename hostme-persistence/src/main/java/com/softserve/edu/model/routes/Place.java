package com.softserve.edu.model.routes;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Place {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
