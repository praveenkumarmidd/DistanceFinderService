package com.wcc.distance.finder.model.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Postcode Reform Response model class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Setter
@Getter
@ApiModel(description = "Distance Calculator Response")
public class PostReform {
    private String status;
    private String description;

    public PostReform(String status, String description) {
        this.status = status;
        this.description = description;
    }
}
