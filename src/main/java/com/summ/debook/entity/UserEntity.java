package com.summ.debook.entity;
// Generated Jan 23, 2016 2:48:02 AM by Hibernate Tools 4.3.1.Final

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserEntity generated by hbm2java
 */
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "login")
        }
)
public class UserEntity implements java.io.Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @JsonIgnore
    @Column(name = "login", nullable = false, length = 45)
    private String login;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", length = 45)
    private String surname;

    @Column(name = "email", unique = true, nullable = false, length = 45)
    private String email;

    @JsonIgnore
    @Column(name = "activated", nullable = false)
    private boolean activated;

    //TODO LAZY
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<AuthoritiesEntity> authorities = new HashSet<>(0);

    //TODO LAZY
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ownerUser")
    private List<PersonEntity> persons = new ArrayList<>(0);

    public UserEntity() {
    }

    public UserEntity(Long userId, String login, String name, String email, boolean activated) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.email = email;
        this.activated = activated;
    }

    public UserEntity(Long userId, String login, String name, String surname, String email, boolean activated,
                      Set<AuthoritiesEntity> authorities, List<PersonEntity> persons) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.activated = activated;
        this.authorities = authorities;
        this.persons = persons;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<AuthoritiesEntity> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<AuthoritiesEntity> authorities) {
        this.authorities = authorities;
    }

    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", activated=" + activated +
                ", authorities=" + authorities +
                ", persons=" + persons +
                '}';
    }
}
