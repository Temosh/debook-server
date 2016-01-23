package com.summ.debook.entity;
// Generated Jan 23, 2016 2:48:02 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * UserSecretEntity generated by hbm2java
 */
@Entity
@Table(name = "user_secret", catalog = "debook_db")
public class UserSecretEntity implements java.io.Serializable {

    private int userId;
    private UserEntity user;
    private String hash;

    public UserSecretEntity() {
    }

    public UserSecretEntity(UserEntity user, String hash) {
        this.user = user;
        this.hash = hash;
    }

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user") )
    @Id
    @GeneratedValue(generator = "generator")

    @Column(name = "user_id", unique = true, nullable = false)
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(name = "hash", nullable = false, length = 45)
    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
