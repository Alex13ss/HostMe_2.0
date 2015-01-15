package com.softserve.edu.model.routes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.model.City;
import com.softserve.edu.model.User;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "attendee")
    private Set<User> attendee;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getAttendee() {
        return attendee;
    }

    public void setAttendee(Set<User> attendee) {
        this.attendee = attendee;
    }
}
