package com.softserve.edu.model.routes;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(mappedBy = "places", cascade = CascadeType.ALL)
    Set<Route> routes;
}
