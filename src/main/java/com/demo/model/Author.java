package com.demo.model;

import com.demo.model.listener.TimestampListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * Holds the details associated with the {@link Book}.
 */
@Entity
@Table(name = "author")
@EntityListeners(value = TimestampListener.class)
public class Author extends Modifiable {

    @Column(name = "giver_name", nullable = false)
    private String givenName;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
