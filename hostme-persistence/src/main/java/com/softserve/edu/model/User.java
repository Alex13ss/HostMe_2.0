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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.model.routes.Place;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @Size(min = 3, message = "Login must be at least 3 characters!")
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
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER/*, orphanRemoval = true*/)
    private Set<Place> places;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.DELETE, CascadeType.PERSIST })
    @JsonIgnore
    @JoinTable(name = "user_place", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Place> attendee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "favourite_sightseeing", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Sightseeing> favouriveSights;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", 
            cascade = javax.persistence.CascadeType.REMOVE, orphanRemoval = true)
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
     * Contains interesting groups, submitted by this user
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "interestedUsers", cascade = javax.persistence.CascadeType.ALL)
    private List<Group> interestingGroups = new ArrayList<Group>();

    /**
     * Contains notifications of group, submitted by this user
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "toReceiveUsers")
    private List<Notification> receivedNotifications;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_bookedPlaces",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Place> bookedPlaces;
    
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

    public List<Group> getInterestingGroups() {
        return interestingGroups;
    }

    public void setInterestingGroups(List<Group> interestingGroups) {
        this.interestingGroups = interestingGroups;
    }

    public List<Notification> getReceivedNotifications() {
        return receivedNotifications;
    }

    public void setReceivedNotifications(
            List<Notification> receivedNotifications) {
        this.receivedNotifications = receivedNotifications;
    }

    public Set<Sightseeing> getFavouriveSights() {
        return favouriveSights;
    }

    public void setFavouriveSights(Set<Sightseeing> favouriveSights) {
        this.favouriveSights = favouriveSights;
    }

    public Set<Place> getBookedPlaces() {
        return bookedPlaces;
    }

    public void setBookedPlaces(Set<Place> bookedPlaces) {
        this.bookedPlaces = bookedPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (birthday != null ? !birthday.equals(user.birthday)
                : user.birthday != null)
            return false;
        if (city != null ? !city.equals(user.city) : user.city != null)
            return false;
        if (country != null ? !country.equals(user.country)
                : user.country != null)
            return false;
        if (description != null ? !description.equals(user.description)
                : user.description != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (!firstName.equals(user.firstName))
            return false;
        if (gender != user.gender)
            return false;
        if (hobby != null ? !hobby.equals(user.hobby) : user.hobby != null)
            return false;
        if (!lastName.equals(user.lastName))
            return false;
        if (!login.equals(user.login))
            return false;
        if (region != null ? !region.equals(user.region) : user.region != null)
            return false;
        if (role != null ? !role.equals(user.role) : user.role != null)
            return false;
        if (!userId.equals(user.userId))
            return false;
        if (userState != user.userState)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * login.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + (userState != null ? userState.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (hobby != null ? hobby.hashCode() : 0);
        result = 31 * result
                + (description != null ? description.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
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
