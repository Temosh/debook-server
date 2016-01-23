package com.summ.debook.entity;
// Generated Jan 23, 2016 2:48:02 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AuthoritiesIdEntity generated by hbm2java
 */
@Embeddable
public class AuthoritiesIdEntity implements java.io.Serializable {

    private int userId;
    private String authority;

    public AuthoritiesIdEntity() {
    }

    public AuthoritiesIdEntity(int userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "authority", nullable = false, length = 45)
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof AuthoritiesIdEntity))
            return false;
        AuthoritiesIdEntity castOther = (AuthoritiesIdEntity) other;

        return (this.getUserId() == castOther.getUserId())
                && ((this.getAuthority() == castOther.getAuthority()) || (this.getAuthority() != null
                        && castOther.getAuthority() != null && this.getAuthority().equals(castOther.getAuthority())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getUserId();
        result = 37 * result + (getAuthority() == null ? 0 : this.getAuthority().hashCode());
        return result;
    }

}
