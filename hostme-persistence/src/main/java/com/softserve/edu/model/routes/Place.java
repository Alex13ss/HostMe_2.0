package com.softserve.edu.model.routes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.model.City;

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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToMany(mappedBy = "places", cascade = CascadeType.ALL)
    Set<Route> routes;
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonIgnore
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    @JsonIgnore
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @JsonIgnore
    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    
}
