package com.softserve.edu.model.routes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;

import javax.persistence.*;
import java.util.List;

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
    
    @Column
    private long distance;
    
    @Column int rating;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PriceCategory priceCategory;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "routes_places",
        joinColumns = {@JoinColumn(name = "route_id")},
        inverseJoinColumns = {@JoinColumn(name = "place_id")})
    private List<Place> places;

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

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (distance != route.distance) return false;
        if (id != route.id) return false;
        if (!description.equals(route.description)) return false;
        if (!name.equals(route.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (int) (distance ^ (distance >>> 32));
        return result;
    }
}
