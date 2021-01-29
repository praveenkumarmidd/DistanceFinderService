package com.wcc.distance.finder.model.request;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Postcode Reform Request model class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Getter
@Setter
@ApiModel(description = "Postcode Details")
public class Location {
    @ApiModelProperty(notes = "Postcode to be updated", name = "Postcode", required = true)
    @NotNull
    private String postcode;
    @ApiModelProperty(notes = "latitude", name = "latitude", required = true)
    @NotNull
    private double latitude;
    @ApiModelProperty(notes = "longitude", name = "longitude", required = true)
    @NotNull
    private double longitude;

    public Location(String postCode, double latitude, double longitude) {
        this.postcode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
