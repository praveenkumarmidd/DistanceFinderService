package com.wcc.distance.finder.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Generic Distance Finder Service Response model class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistanceFinderResponse<T> {
    @JsonView
    private final T distanceFinderResponse;

    public DistanceFinderResponse(T distanceFinderResponse) {
        this.distanceFinderResponse = distanceFinderResponse;
    }


}
