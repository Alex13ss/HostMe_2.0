package com.softserve.edu.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.softserve.edu.model.routes.Place;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.model.routes.Route;

/**
 * This class represents data for User object. It uses Hibernate to map java
 * class User to database table USER.
 * 
 * @author Lv-117
 */
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = {
        "user_id", "login", "email" }) })
public class User {

    @Id
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "users_user_id_seq", allocationSize = 101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @Size(min = 3, message = "Login must be at least 3 characters!")
    @JsonIgnore
    @Column(name = "login", length = 50, updatable = false, unique = true)
    private String login;

    @Size(min = 5, message = "Password must be at least 5 characters!")
    @JsonIgnore
    @Column(name = "password", length = 70)
    private String password;

    @Size(min = 3, message = "Name must be at least 3 characters!")
    @Column(name = "firstName", length = 70)
    private String firstName;

    @Size(min = 3, message = "Name must be at least 3 characters!")
    @Column(name = "lastName", length = 70)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    private UserState userState;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date")
    private Calendar birthday;

    @Size(min = 1, message = "Invalid email address!")
    @Email(message = "Invalid email address!")
    @Column(name = "email", unique = true, length = 70)
    private String email;

    @Column(name = "hobby", length = 100)
    private String hobby;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "region", length = 50)
    private String region;

    @ManyToOne
    private City city;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * Contains languages owned by this user
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.DELETE, CascadeType.PERSIST })
    @JoinTable(name = "user_languages", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages = new ArrayList<Language>();

    /**
     * Contains images uploaded by this user
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Image> images = new HashSet<Image>();

    /**
     * Contains hosting apartments owned by this user
     */
    @JsonIgnore
    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Hosting> hostings = new HashSet<Hosting>();

    /**
     * Contains requests submitted by this user
     */
    @JsonIgnore
    @OneToMany(mappedBy = "author", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Request> requests = new HashSet<Request>();

    /**
     * Contains feedbackas provided by this user to hosters
     */
    @JsonIgnore
    @OneToMany(mappedBy = "author", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks = new HashSet<Feedback>();

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Place> places;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.DELETE, CascadeType.PERSIST })
    @JoinTable(name = "user_place", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Place> attendee;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = javax.persistence.CascadeType.ALL)
    private Set<Route> routes = new HashSet<>();

    /**
     * Contains groups which was created by this user
     */
    @JsonIgnore
    @OneToMany(mappedBy = "creatorUser", fetch = FetchType.LAZY)
    private Set<Group> myGroups;

    /**
     * Contains groups which was edited (last editing) by this user
     */
    @JsonIgnore
    @OneToMany(mappedBy = "lastEditor", fetch = FetchType.LAZY)
    private Set<Group> meEditedGroups;

    /**
     * Contains groups submitted by this user
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.DELETE, CascadeType.PERSIST })
    @JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    public User() {
    }

    public User(String firstName, String lastName, Gender gender) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Language> getLanguages() {
        HashSet<Language> langs = new HashSet<Language>(languages);
        languages.clear();
        languages.addAll(langs);
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Set<Hosting> getHostings() {
        return hostings;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public Set<Place> getAttendee() {
        return attendee;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public void setAttendee(Set<Place> attendee) {
        this.attendee = attendee;
    }

    public void addLanguage(Language language) {
        if (language != null && !languages.contains(language)) {
            languages.add(language);
            language.addUser(this);
        }
    }

    public void addImage(Image image) {
        if (image != null && !images.contains(image)) {
            images.add(image);
            image.setUser(this);
        }
    }

    public void addHosting(Hosting hosting) {
        if (hosting != null && !hostings.contains(hosting)) {
            hostings.add(hosting);
            hosting.setOwner(this);
        }
    }

    public void addRequest(Request request) {
        if (request != null && !requests.contains(request)) {
            requests.add(request);
            request.setAuthor(this);
        }
    }

    public void addFeedback(Feedback feedback) {
        if (feedback != null && !feedbacks.contains(feedback)) {
            feedbacks.add(feedback);
            feedback.setAuthor(this);
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public Set<Group> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(Set<Group> myGroups) {
        this.myGroups = myGroups;
    }

    public Set<Group> getMeEditedGroups() {
        return meEditedGroups;
    }

    public void setMeEditedGroups(Set<Group> meEditedGroups) {
        this.meEditedGroups = meEditedGroups;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", login=" + login + ", password="
                + password + ", firstName=" + firstName + ", lastName="
                + lastName + ", gender=" + gender + ", birthday=" + birthday
                + ", email=" + email + ", about=" + ", country=" + country
                + ", region=" + region + "]";
    }

}
