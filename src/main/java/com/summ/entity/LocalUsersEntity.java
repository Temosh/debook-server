package com.summ.entity;

import javax.persistence.*;

/**
 * Created by svtem on 14.09.2015.
 */
@Entity
@Table(name = "local_users", schema = "", catalog = "debook_db")
@IdClass(LocalUsersEntityPK.class)
public class LocalUsersEntity {
    private int localUserId;
    private int userId;
    private String name;
    private String surname;

    @Basic
    @Column(name = "local_user_id")
    public int getLocalUserId() {
        return localUserId;
    }

    public void setLocalUserId(int localUserId) {
        this.localUserId = localUserId;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalUsersEntity that = (LocalUsersEntity) o;

        if (localUserId != that.localUserId) return false;
        if (userId != that.userId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = localUserId;
        result = 31 * result + userId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
