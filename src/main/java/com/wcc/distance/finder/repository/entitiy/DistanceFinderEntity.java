package com.wcc.distance.finder.repository.entitiy;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Distance Finder Entity class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */

@Getter
@Setter
@Entity
@Table(name = "postcodelatlng")
public class DistanceFinderEntity {
    @Id
    @Column(name = "postCode")
    @NotNull
    private String postCode;

    @Column(name = "latitude")
    @NotNull
    private double latitude;

    @Column(name = "longitude")
    @NotNull
    private double longitude;

    public DistanceFinderEntity() {

    }

    public DistanceFinderEntity(String postCode, double latitude, double longitude) {
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
