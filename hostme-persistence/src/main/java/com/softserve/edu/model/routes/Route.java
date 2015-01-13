package com.softserve.edu.model.routes;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROUTES")
public class Route {

    @Id
    @GeneratedValue
    private int id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "routes_places",
        joinColumns = {@JoinColumn(name = "route_id")},
        inverseJoinColumns = {@JoinColumn(name = "place_id")})
    private Set<Place> places;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
